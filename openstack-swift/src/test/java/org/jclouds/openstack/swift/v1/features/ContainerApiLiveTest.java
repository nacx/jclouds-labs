/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.openstack.swift.v1.features;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.jclouds.openstack.swift.v1.domain.Container;
import org.jclouds.openstack.swift.v1.internal.BaseSwiftApiLiveTest;
import org.testng.annotations.Test;

import com.google.common.collect.FluentIterable;

/**
 * @author Adrian Cole
 */
@Test(groups = "live", testName = "ContainerApiLiveTest")
public class ContainerApiLiveTest extends BaseSwiftApiLiveTest {

   @Test
   public void testListContainers() throws Exception {
      for (String regionId : api.configuredRegions()) {
         ContainerApi containerApi = api.containerApiInRegion(regionId);
         FluentIterable<? extends Container> response = containerApi.list();
         assertNotNull(response);
         for (Container container : response) {
            assertNotNull(container.name());
            assertTrue(container.objectCount() >= 0);
            assertTrue(container.bytesUsed() >= 0);
         }
      }
   }
}