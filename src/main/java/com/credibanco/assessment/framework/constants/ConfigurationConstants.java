package com.credibanco.assessment.framework.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 
 * @author ajrozo
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfigurationConstants {

    public static final String SPRING_DATASOURCE = "spring.datasource";
    public static final String SPRING_DATASOURCE_BEAN = "datasource-spring-config";
    public static final String DATASOURSE_URL = "${spring.datasource.url}";
    public static final String REQUEST_CARDS = "cards";
    public static final String RESPONSE_STATUS = "responseStatus";
    public static final String REQUEST_CREATE_CARD = "create-card";
    public static final String REQUEST_ENROLL_CARD = "enroll-card";
    public static final String REQUEST_FIND_CARD = "find-card";
    public static final String REQUEST_FIND_TRANSACTION = "find-transaction";
    public static final String REQUEST_DELETE_CARD = "delete-card";
    public static final String REQUEST_TRANSACTIONS = "transactions";
    public static final String REQUEST_CREATE_TRANSACTION = "create-transaction";
    public static final String REQUEST_CANCEL_TRANSACTION = "cancel-transaction";
    public static final String SIMPLE_DATE_FORMAT = "dd-MM-yyyy";
    public static final String SIMPLE_DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";

}
