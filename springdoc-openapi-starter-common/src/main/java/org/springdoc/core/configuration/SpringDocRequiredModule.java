/*
 *
 *  *
 *  *  *
 *  *  *  * Copyright 2019-2022 the original author or authors.
 *  *  *  *
 *  *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  *  * you may not use this file except in compliance with the License.
 *  *  *  * You may obtain a copy of the License at
 *  *  *  *
 *  *  *  *      https://www.apache.org/licenses/LICENSE-2.0
 *  *  *  *
 *  *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  *  * See the License for the specific language governing permissions and
 *  *  *  * limitations under the License.
 *  *  *
 *  *
 *
 */

package org.springdoc.core.configuration;

import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * The type Spring doc required module.
 *
 * @author bnasslahsen
 */
public class SpringDocRequiredModule extends SimpleModule {

	@Override
	public void setupModule(SetupContext context) {
		context.insertAnnotationIntrospector(new RespectSchemaRequiredAnnotationIntrospector());
	}

	/**
	 * The type Respect schema required annotation introspector.
	 */
	private static class RespectSchemaRequiredAnnotationIntrospector extends NopAnnotationIntrospector {
		@Override
		public Boolean hasRequiredMarker(AnnotatedMember m) {
			Schema schemaAnnotation = m.getAnnotation(Schema.class);
			return schemaAnnotation != null ? schemaAnnotation.required() : null;
		}
	}
}
