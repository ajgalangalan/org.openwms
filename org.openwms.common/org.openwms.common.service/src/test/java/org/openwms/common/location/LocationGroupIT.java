/*
 * openwms.org, the Open Warehouse Management System.
 * Copyright (C) 2014 Heiko Scherrer
 *
 * This file is part of openwms.org.
 *
 * openwms.org is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as 
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * openwms.org is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this software. If not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.openwms.common.location;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.openwms.core.test.IntegrationTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * A LocationGroupIT.
 *
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version 1.0
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@IntegrationTest
public class LocationGroupIT {

    private static final String KNOWN_LG = "KNOWN";
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationGroupIT.class);

    private LocationGroup knownLG;

    @Autowired
    private TestEntityManager entityManager;

    /**
     * Setup data.
     */
    @Before
    public void onBefore() {
        knownLG = new LocationGroup(KNOWN_LG);
        entityManager.persist(knownLG);
        entityManager.flush();
        entityManager.clear();
    }


    /**
     * Creating two groups with same id must fail.
     */
    @Test
    public final void testNameConstraint() {
        thrown.expect(IllegalArgumentException.class);
        entityManager.persist(new LocationGroup(KNOWN_LG));
    }
}
