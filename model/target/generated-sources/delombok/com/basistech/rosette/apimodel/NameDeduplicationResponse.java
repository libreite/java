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
package com.basistech.rosette.apimodel;

import com.basistech.rosette.annotations.JacksonMixin;
import java.util.List;

/**
 * Response data model for the deduplication of a list of names
 */
@JacksonMixin
public class NameDeduplicationResponse extends Response {
    /**
     */
    private final List<String> results;

    @java.beans.ConstructorProperties({"results"})
    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    NameDeduplicationResponse(final List<String> results) {
        this.results = results;
    }


    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public static class NameDeduplicationResponseBuilder {
        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        private List<String> results;

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        NameDeduplicationResponseBuilder() {
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public NameDeduplicationResponseBuilder results(final List<String> results) {
            this.results = results;
            return this;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public NameDeduplicationResponse build() {
            return new NameDeduplicationResponse(results);
        }

        @Override
        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public String toString() {
            return "NameDeduplicationResponse.NameDeduplicationResponseBuilder(results=" + this.results + ")";
        }
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public static NameDeduplicationResponseBuilder builder() {
        return new NameDeduplicationResponseBuilder();
    }

    /**
     * @return name deduplication results
     */
    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public List<String> getResults() {
        return this.results;
    }

    @Override
    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof NameDeduplicationResponse)) return false;
        final NameDeduplicationResponse other = (NameDeduplicationResponse) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$results = this.getResults();
        final Object other$results = other.getResults();
        if (this$results == null ? other$results != null : !this$results.equals(other$results)) return false;
        return true;
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    protected boolean canEqual(final Object other) {
        return other instanceof NameDeduplicationResponse;
    }

    @Override
    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + super.hashCode();
        final Object $results = this.getResults();
        result = result * PRIME + ($results == null ? 43 : $results.hashCode());
        return result;
    }
}