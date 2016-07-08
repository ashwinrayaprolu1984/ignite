/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.yardstick.cache;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteDataStreamer;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.yardstick.cache.model.Organization;
import org.apache.ignite.yardstick.cache.model.Person;
import org.yardstickframework.BenchmarkConfiguration;

import static org.yardstickframework.BenchmarkUtils.println;

/**
 * Ignite benchmark that performs query operations with joins.
 */
public class IgniteSqlQueryDistributedJoinBenchmark extends IgniteCacheAbstractBenchmark<Integer, Object> {
    /** */
    private int orgRange;

    /** {@inheritDoc} */
    @Override public void setUp(BenchmarkConfiguration cfg) throws Exception {
        super.setUp(cfg);

        println(cfg, "Populating query data...");

        long start = System.nanoTime();

        try (IgniteDataStreamer<Object, Object> dataLdr = ignite().dataStreamer(cache.getName())) {
            orgRange = args.range() / 10;

            if (orgRange <= 0)
                throw new IllegalArgumentException();

            // Populate organizations.
            for (int orgId = 0; orgId < orgRange; orgId++) {
                dataLdr.addData(orgId, new Organization(orgId, "org" + orgId));

                if (orgId % 1000 == 0 && Thread.currentThread().isInterrupted())
                    return;
            }

            dataLdr.flush();

            int personCnt = 0;

            // Populate persons.
            for (int orgId = 0; orgId < orgRange; orgId++) {
                int persons = orgId % 10 + 1;

                for (int j = 0; j < persons; j++) {
                    int personId = orgRange + personCnt++;

                    Person p = new Person(personId,
                        orgId,
                        "firstName" + personId,
                        "lastName" + personId, 1000);

                    dataLdr.addData(personId, p);
                }

                if (personCnt % 100000 == 0)
                    println(cfg, "Populated persons: " + personCnt);

                if (orgId % 1000 == 0 && Thread.currentThread().isInterrupted())
                    return;
            }

            dataLdr.close();
        }

        println(cfg, "Finished populating join query data in " + ((System.nanoTime() - start) / 1_000_000) + " ms.");

        executeQueryJoin(0, args.broadcastJoin(), true);
    }

    /** {@inheritDoc} */
    @Override public boolean test(Map<Object, Object> ctx) throws Exception {
        int orgId = nextRandom(orgRange);

        Collection<List<?>> res = executeQueryJoin(orgId, args.broadcastJoin(), false);

        int persons = orgId % 10 + 1;

        if (res.size() != persons)
            throw new Exception("Invalid join result [orgId=" + orgId + ", resSize=" + res.size() + ']');

        for (List<?> l : res) {
            int orgId0 = (Integer)l.get(1);

            if (orgId != orgId0)
                throw new Exception("Invalid join result [orgId=" + orgId + ", res=" + l + ']');
        }

        return true;
    }

    /**
     * @param orgId Organization ID.
     * @param broadcast Broadcast join flag.
     * @param planOnly If {@code true} just prints query plan.
     * @return Query results.
     * @throws Exception If failed.
     */
    private Collection<List<?>> executeQueryJoin(int orgId, boolean broadcast, boolean planOnly) throws Exception {
        SqlFieldsQuery qry;

        String sql;

        if (broadcast) {
            sql = "select p.id, p.orgId, p.firstName, p.lastName, o.name " +
                "from Organization o " +
                "join Person p " +
                "on p.orgId = o._key " +
                "where o._key=?";
        }
        else {
            sql = "select p.id, p.orgId, p.firstName, p.lastName, o.name " +
                "from Person p " +
                "join Organization o " +
                "on p.orgId = o._key " +
                "where o._key=?";
        }

        qry = new SqlFieldsQuery(planOnly ? ("explain " + sql) : sql);
        qry.setEnforceJoinOrder(true);
        qry.setDistributedJoins(true);
        qry.setArgs(orgId);

        if (planOnly) {
            String plan = (String)cache.query(qry).getAll().get(0).get(0);

            println("Query execution plan:\n" + plan);

            return null;
        }
        else
            return cache.query(qry).getAll();
    }

    /** {@inheritDoc} */
    @Override protected IgniteCache<Integer, Object> cache() {
        return ignite().cache("query");
    }
}