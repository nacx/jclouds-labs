/*
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.jclouds.googlecomputeengine.features;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.jclouds.collect.IterableWithMarker;
import org.jclouds.collect.PagedIterable;
import org.jclouds.googlecomputeengine.domain.Kernel;
import org.jclouds.googlecomputeengine.internal.BaseGoogleComputeEngineApiLiveTest;
import org.jclouds.googlecomputeengine.options.ListOptions;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

/**
 * @author David Alves
 */
public class KernelApiLiveTest extends BaseGoogleComputeEngineApiLiveTest {

   private Kernel kernel;

   private KernelApi api() {
      return api.getKernelApiForProject("google");
   }

   @Test(groups = "live")
   public void testListKernel() {

      PagedIterable<Kernel> kernels = api().list(new ListOptions.Builder()
              .maxResults(1));

      Iterator<IterableWithMarker<Kernel>> pageIterator = kernels.iterator();
      assertTrue(pageIterator.hasNext());

      IterableWithMarker<Kernel> singlePageIterator = pageIterator.next();
      List<Kernel> kernelAsList = Lists.newArrayList(singlePageIterator);

      assertSame(kernelAsList.size(), 1);

      this.kernel = Iterables.getOnlyElement(kernelAsList);
   }


   @Test(groups = "live", dependsOnMethods = "testListKernel")
   public void testGetKernel() {
      Kernel kernel = api().get(this.kernel.getName());
      assertNotNull(kernel);
      assertKernelEquals(kernel, this.kernel);
   }

   private void assertKernelEquals(Kernel result, Kernel expected) {
      assertEquals(result.getName(), expected.getName());
   }

}