/******************************************************************************
 ** This data and information is proprietary to, and a valuable trade secret
 ** of, Basis Technology Corp.  It is given in confidence by Basis Technology
 ** and may only be used as permitted under the license agreement under which
 ** it has been distributed, and in no other way.
 **
 ** Copyright (c) 2015 Basis Technology Corporation All rights reserved.
 **
 ** The technical data and information provided herein are provided with
 ** `limited rights', and the computer software provided herein is provided
 ** with `restricted rights' as those terms are defined in DAR and ASPR
 ** 7-104.9(a).
 ******************************************************************************/

package com.basistech.rosette.model;

import java.util.List;

/**
 * Simple api response data model for entity resolver 
 */
public final class LinkedEntityResponse extends Response {

    private List<LinkedEntity> entities;

    public LinkedEntityResponse() { super(); }

    /**
     * constructor for {@code LinkedEntityResponse}
     * @param requestId request id
     * @param entities list of resolved entities
     */
    public LinkedEntityResponse(
            String requestId,
            List<LinkedEntity> entities) {
        super(requestId);
        this.entities = entities;
    }

    /**
     * get the list of resolved (against the knowledgebase) entites
     * @return the list of resolved entities
     */
    public List<LinkedEntity> getEntities() {
        return entities;
    }

    /**
     * set the list of resolved (against the knowledgebase) entities
     * @param entities the list of resolved entities
     */
    public void setEntities(List<LinkedEntity> entities) {
        this.entities = entities;
    }

    @Override
    public int hashCode() {
        return entities != null ? entities.hashCode() : 0;
    }

    /**
     * if the param is a {@code LinkedEntityResponse}, compare contents for equality
     * @param o the object
     * @return whether or not the param object is equal to this object
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof LinkedEntityResponse) {
            LinkedEntityResponse that = (LinkedEntityResponse) o;
            return super.equals(o)
                    && entities != null ? entities.equals(that.getEntities()) : entities == that.getEntities();
        } else {
            return false;
        }
    }
}