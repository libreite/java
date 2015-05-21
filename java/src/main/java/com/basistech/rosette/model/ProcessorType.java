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

public enum ProcessorType {
    /** The statistical entity extractor. */
    statistical,
    /** The gazetteer based entity extractor. */
    acceptGazetteer,
    /** The regular expression based entity extractor. */
    acceptRegex,
    /** The gazetteer based entity rejector. */
    rejectGazetteer,
    /** The regular expression based entity rejector. */
    rejectRegex,
    /** The entity redactor, which disambiguates overlapping entities. */
    redactor,
    /** The entity joiner, which joins adjacent entities. */
    joiner
}
