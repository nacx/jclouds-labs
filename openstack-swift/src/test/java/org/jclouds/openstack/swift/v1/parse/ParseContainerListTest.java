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
package org.jclouds.openstack.swift.v1.parse;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.jclouds.json.BaseSetParserTest;
import org.jclouds.openstack.swift.v1.domain.Container;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableSet;

/**
 * @author Adrian Cole
 */
@Test(groups = "unit", testName = "ParseContainerListTest")
public class ParseContainerListTest extends BaseSetParserTest<Container> {

   @Override
   public String resource() {
      return "/container_list.json";
   }

   @Override
   @Consumes(MediaType.APPLICATION_JSON)
   public Set<Container> expected() {
      return ImmutableSet
            .of(Container.builder()
                  .name("test_container_1")
                  .objectCount(2)
                  .bytesUsed(78)
                  .build(),
                Container.builder()
                  .name("test_container_2")
                  .objectCount(1)
                  .bytesUsed(17)
                  .build());
   }
}