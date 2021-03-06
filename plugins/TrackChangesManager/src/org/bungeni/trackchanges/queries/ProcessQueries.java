package org.bungeni.trackchanges.queries;


/**
 *
 * @author Ashok Hariharan
 */
public class ProcessQueries {

    public static String INSERT_PROCESS (String processId, String billId, String processStatus) {
        return "insert into process ( process_id, bill_id, process_date, process_status) " +
                "values ('"+
                            processId
                            + "','" +
                            billId
                            + "'," +
                            "now()"
                            + ",'" +
                            processStatus
                 + "')";
    }

    public static String GET_LATEST_PROCESS_FOR_BILL(String billId) {
        String strQuery = "select process_id, bill_id, process_date from process " +
                "where bill_id = '"+ billId + "' and process_date in (" +
                "   select max(process_date) from process " +
                ")";
        return strQuery ;
    }
    
    public static String GET_LATEST_DOCS_FOR_BILL(String billId) {
        String strQuery = "" +
                "select process_id, doc_name, doc_path, doc_owner   from process_documents where " +
                "process_id in ( " +
                    "select process_id from process " +
                    "where process_date in (  " +
                        "select max(process_date) from process  " +
                        "where bill_id = '" + billId + "'" +
                     ")" +
                ")";
        return strQuery;
    }

    public static String GET_LATEST_AMENDS(String processId, String docName, String docAuthor) {
        String strQuery = " " +
                "select process_id, doc_name, doc_owner, change_id, change_action, change_date, change_text, change_status " +
                " from process_amends " +
                "where process_id = '" + processId + "' and " +
                "doc_name='" + docName + "' and " +
                "doc_owner = '" + docAuthor + "' ";
        return strQuery;
    }

    public static String UPDATE_AMEND_STATUS (String processId, String docName, String docAuthor, String changeId, Boolean newStatus) {
        String strQuery = "update process_amends " +
                            " set change_status = " + newStatus +
                                " where process_id = '" + processId + "' and " +
                                " doc_name='" + docName + "' and " +
                                " doc_owner = '" + docAuthor + "' and " +
                                " change_id = '" + changeId + "' ";
        
        return strQuery;
    }


    public static String INSERT_PROCESS_DOCS (String processId, String filename, String documentPath, String docAuthor, Long checkSum) {
        return "insert into process_documents " +
                "( process_id, doc_name, doc_path, doc_owner, doc_crc) values ('"+
                 processId
                 + "','" +
                 filename
                 + "','" +
                 documentPath
                 + "','" +
                 docAuthor
                 + "'," +
                 checkSum+ 
                 ")";
    }

    public static String INSERT_PROCESS_AMENDS (
            String processId,
            String docName,
            String docAuthor,
            String changeId,
            String changeType ,
            String changeDate,
            String changeText,
            String changeStatus ) {
        return "insert into process_amends " +
                "(PROCESS_ID, DOC_NAME, DOC_OWNER, CHANGE_ID, CHANGE_ACTION, CHANGE_DATE, CHANGE_TEXT, CHANGE_STATUS)" +
                " values ('" +
                processId
                + "','"+
                docName
                + "','"+
                docAuthor
                + "','" +
                changeId
                + "','" +
                changeType
                + "','" +
                changeDate
                + "','" +
                changeText
                + "'," +
                changeStatus
                + ")";
    }

}
