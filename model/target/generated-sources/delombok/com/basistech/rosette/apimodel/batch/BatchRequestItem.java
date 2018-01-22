// Generated by delombok at Mon Jan 22 15:21:35 EST 2018
/*
* Copyright 2017 Basis Technology Corp.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.basistech.rosette.apimodel.batch;

import com.basistech.rosette.annotations.JacksonMixin;
import com.basistech.rosette.apimodel.Request;

/**
 * One item in a batch request. Each item consists of an input
 * to process and an endpoint to apply to the item.
 * The inputs are specified by {@link Request} objects.
 * If the request type is {@link com.basistech.rosette.apimodel.DocumentRequest}, the special content type
 * {@code application/vnd.basistech-multiple-inputs}
 * used in {@link com.basistech.rosette.apimodel.DocumentRequest#contentType} identifies
 * a json file consisting of an array of strings; each string being treated as an
 * input. The string length in this case is limited to 200 characters.
 */
@JacksonMixin
public final class BatchRequestItem {
    private final String endpoint;
    private final Request request;
    private final String id;

    @java.beans.ConstructorProperties({"endpoint", "request", "id"})
    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    BatchRequestItem(final String endpoint, final Request request, final String id) {
        this.endpoint = endpoint;
        this.request = request;
        this.id = id;
    }


    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public static class BatchRequestItemBuilder {
        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        private String endpoint;
        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        private Request request;
        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        private String id;

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        BatchRequestItemBuilder() {
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public BatchRequestItemBuilder endpoint(final String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public BatchRequestItemBuilder request(final Request request) {
            this.request = request;
            return this;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public BatchRequestItemBuilder id(final String id) {
            this.id = id;
            return this;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public BatchRequestItem build() {
            return new BatchRequestItem(endpoint, request, id);
        }

        @Override
        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public String toString() {
            return "BatchRequestItem.BatchRequestItemBuilder(endpoint=" + this.endpoint + ", request=" + this.request + ", id=" + this.id + ")";
        }
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public static BatchRequestItemBuilder builder() {
        return new BatchRequestItemBuilder();
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public String getEndpoint() {
        return this.endpoint;
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public Request getRequest() {
        return this.request;
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public String getId() {
        return this.id;
    }

    @Override
    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof BatchRequestItem)) return false;
        final BatchRequestItem other = (BatchRequestItem) o;
        final Object this$endpoint = this.getEndpoint();
        final Object other$endpoint = other.getEndpoint();
        if (this$endpoint == null ? other$endpoint != null : !this$endpoint.equals(other$endpoint)) return false;
        final Object this$request = this.getRequest();
        final Object other$request = other.getRequest();
        if (this$request == null ? other$request != null : !this$request.equals(other$request)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        return true;
    }

    @Override
    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $endpoint = this.getEndpoint();
        result = result * PRIME + ($endpoint == null ? 43 : $endpoint.hashCode());
        final Object $request = this.getRequest();
        result = result * PRIME + ($request == null ? 43 : $request.hashCode());
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        return result;
    }

    @Override
    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public String toString() {
        return "BatchRequestItem(endpoint=" + this.getEndpoint() + ", request=" + this.getRequest() + ", id=" + this.getId() + ")";
    }
}