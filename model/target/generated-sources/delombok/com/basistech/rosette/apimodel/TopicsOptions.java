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
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

/**
 * Options for topics requests
 */
@JacksonMixin
public final class TopicsOptions extends Options {
    /**
     */
    @DecimalMin("0.0")
    @DecimalMax("1.0")
    private final Double conceptSalienceThreshold;
    /**
     */
    @DecimalMin("0.0")
    @DecimalMax("1.0")
    private final Double keyphraseSalienceThreshold;

    @java.beans.ConstructorProperties({"conceptSalienceThreshold", "keyphraseSalienceThreshold"})
    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    TopicsOptions(final Double conceptSalienceThreshold, final Double keyphraseSalienceThreshold) {
        this.conceptSalienceThreshold = conceptSalienceThreshold;
        this.keyphraseSalienceThreshold = keyphraseSalienceThreshold;
    }


    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public static class TopicsOptionsBuilder {
        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        private Double conceptSalienceThreshold;
        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        private Double keyphraseSalienceThreshold;

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        TopicsOptionsBuilder() {
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public TopicsOptionsBuilder conceptSalienceThreshold(final Double conceptSalienceThreshold) {
            this.conceptSalienceThreshold = conceptSalienceThreshold;
            return this;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public TopicsOptionsBuilder keyphraseSalienceThreshold(final Double keyphraseSalienceThreshold) {
            this.keyphraseSalienceThreshold = keyphraseSalienceThreshold;
            return this;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public TopicsOptions build() {
            return new TopicsOptions(conceptSalienceThreshold, keyphraseSalienceThreshold);
        }

        @Override
        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public String toString() {
            return "TopicsOptions.TopicsOptionsBuilder(conceptSalienceThreshold=" + this.conceptSalienceThreshold + ", keyphraseSalienceThreshold=" + this.keyphraseSalienceThreshold + ")";
        }
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public static TopicsOptionsBuilder builder() {
        return new TopicsOptionsBuilder();
    }

    /**
     * @return concept salience threshold (0.0-1.0)
     */
    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public Double getConceptSalienceThreshold() {
        return this.conceptSalienceThreshold;
    }

    /**
     * @return key phrase salience threshold (0.0-1.0)
     */
    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public Double getKeyphraseSalienceThreshold() {
        return this.keyphraseSalienceThreshold;
    }

    @Override
    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TopicsOptions)) return false;
        final TopicsOptions other = (TopicsOptions) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$conceptSalienceThreshold = this.getConceptSalienceThreshold();
        final Object other$conceptSalienceThreshold = other.getConceptSalienceThreshold();
        if (this$conceptSalienceThreshold == null ? other$conceptSalienceThreshold != null : !this$conceptSalienceThreshold.equals(other$conceptSalienceThreshold)) return false;
        final Object this$keyphraseSalienceThreshold = this.getKeyphraseSalienceThreshold();
        final Object other$keyphraseSalienceThreshold = other.getKeyphraseSalienceThreshold();
        if (this$keyphraseSalienceThreshold == null ? other$keyphraseSalienceThreshold != null : !this$keyphraseSalienceThreshold.equals(other$keyphraseSalienceThreshold)) return false;
        return true;
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    protected boolean canEqual(final Object other) {
        return other instanceof TopicsOptions;
    }

    @Override
    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + super.hashCode();
        final Object $conceptSalienceThreshold = this.getConceptSalienceThreshold();
        result = result * PRIME + ($conceptSalienceThreshold == null ? 43 : $conceptSalienceThreshold.hashCode());
        final Object $keyphraseSalienceThreshold = this.getKeyphraseSalienceThreshold();
        result = result * PRIME + ($keyphraseSalienceThreshold == null ? 43 : $keyphraseSalienceThreshold.hashCode());
        return result;
    }

    @Override
    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public String toString() {
        return "TopicsOptions(conceptSalienceThreshold=" + this.getConceptSalienceThreshold() + ", keyphraseSalienceThreshold=" + this.getKeyphraseSalienceThreshold() + ")";
    }
}