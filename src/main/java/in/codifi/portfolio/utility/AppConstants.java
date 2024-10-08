package in.codifi.portfolio.utility;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

public class AppConstants {

	public static final String APPLICATION_JSON = "application/json";
	public static final String TEXT_PLAIN = "text/plain";
	public static final String AUTHORIZATION = "Authorization";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String GET_METHOD = "GET";
	public static final String POST_METHOD = "POST";
	public static final String PUT_METHOD = "PUT";
	public static final String DELETE_METHOD = "DELETE";
	public static final String UTF_8 = "utf-8";
	public static final String ACCEPT = "Accept";
	public static final String BEARER_WITH_SPACE = "Bearer ";

	public static final String SUCCESS_STATUS = "Success";
	public static final String FAILED_STATUS = "Failed";
	public static final int FAILED = 0;
	public static final String STATUS_OK = "Ok";
	public static final String STATUS_NOT_OK = "Not ok";
	public static final String FAILED_CODE = "400";
	public static final String SUCCESS_CODE = "200";
	public static final String UNAUTHORIZED = "Unauthorized";
	public static final List<JSONObject> EMPTY_ARRAY = new ArrayList<>();
	public static final int SUCCESS = 1;

	public static final String ERROR_MIN_CHAR = "Minimum 2 characters required";
	public static final String INVALID_PARAMETER = "Invalid Parameter";
	public static final String NOT_FOUND = "No record found";
	public static final String RECORD_DELETED = "Record Deleted";
	public static final String DELETE_FAILED = "Failed to deleted";
	public static final String NO_DATA = "No Data";
	public static final String TOKEN_NOT_EXISTS = "The token does not exists";
	public static final String INVALID_USER_SESSION = "Invalid user session";
	public static final String GUEST_USER_ERROR = "Guest User";
	public static final String ACTIVE_USER = "ACTIVE_USER";
	public static final String PAYOUT_LIMIT_EXCEEDED = "payout limit exceeded";

	public static final String REST_SUCCESS_STATUS = "success";
	public static final String REST_ERROR_STATUS = "error";

	/**
	 * For Cache
	 */
	public static final String FETCH_DATA_FROM_CACHE = "fetchDataFromCache";
	public static final String HAZEL_KEY_REST_SESSION = "_REST_SESSION";

	public static final String CONTRACT_LOAD_SUCESS = "Contract loaded successfully";
	public static final String CONTRACT_LOAD_FAILED = "Failed to load contract";
	public static final String CACHE_LOADED = "Cache loaded successfully";

	public static final String JDATA = "jData";
	public static final String JKEY = "jKey";
	public static final String SYMBOL_EQUAL = "=";
	public static final String SYMBOL_AND = "&";

	public static final String REST_STATUS_NOT_OK = "not_ok";
	public static final String REST_STATUS_OK = "Ok";
	public static final String REST_NO_DATA = "no data";
	public static final String NO_RECORD_FOUND = "No records are found";

	public static final String MODULE_FUNDS = "Funds";

	public static final String PAYMENT_FAILED_ID_NULL = "Payment Creation Failed referId is null!";
	public static final String AMOUNT_ZERO = "Amount is Zero";
	public static final String NOTES = "notes";
	public static final String UPI = "upi";
	public static final String NET_BANKING = "netbanking";
	public static final String AMOUNT = "amount";
	public static final String CURRENCY = "currency";
	public static final String ONHOLD = "on_hold";
	public static final String RECEIPT = "receipt";
	public static final String BANK_ACCOUNT = "bank_account";
	public static final String METHOD = "method";
	public static final String RAZORPAY_CURRENCY_INR = "INR";
	public static final String ACCOUNT_NUMBER = "account_number";
	public static final String ACCOUNT_NAME = "name";
	public static final String ACCOUNT_IFSC = "ifsc";
	public static final String EMPTY_PARAMETER = "The parameter is null";
	public static final String PAYMENT_CREATION_FAILED = "Payment Creation Failed Check Server!";
	public static final String INTERNAL_ERROR = "Something went wrong. Please try again later";

//	public static final String X_API_KEY = "gZkbrXhn8A4UKEjN799BC9KOWbPzvSSq8Ta1Np0O";
	public static final String X_API_KEY_NAME = "x-api-key";
//	public static final String USER_SESSION = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtZW1iZXJJZCI6NzA3MDcwLCJ1c2VyaWQiOjcwNzA3MCwidGVuYW50aWQiOjcwNzA3MCwibWVtYmVySW5mbyI6eyJ0ZW5hbnRJZCI6IjE0MDQiLCJncm91cElkIjoiSE8iLCJ1c2VySWQiOiJBUElURVNUIiwidGVtcGxhdGVJZCI6IlNUQVRJQzEiLCJ1ZElkIjoiNWMzNDBiZDctNWJkMy00YWFmLTlkMDMtYjI2NjI2YjE2ODU4Iiwib2NUb2tlbiI6IjB4MDFDRDlCRTc3QzU2RjEzRkRFQTJDQkE2MjU4MzkxIiwidXNlckNvZGUiOiJOV1NZRiIsImdyb3VwQ29kZSI6IkFBQUFBIiwiYXBpa2V5RGF0YSI6eyJDdXN0b21lcklkIjoiMTQwNCIsImV4cCI6MTY4OTA0NDg4MCwiaWF0IjoxNjU3NTA4OTIxfSwic291cmNlIjoiTU9CSUxFQVBJIn0sImV4cCI6MTY4NDQzNDU5OSwiaWF0IjoxNjg0Mzg4ODU0fQ.pW4fDMUE_E0P40yTRMykBZ-YE74bFP9TuAD1n0_ULV0";

	// payment
	public static final String PAYMENT_ALREADY_CREATED = "Payment already Created";
	public static final String USER_ID_NULL = "The given user Id is null";
	public static final String AMOUNT_NULL = "Amount is Zero";
	public static final String PARAMETER_NULL = "The Given Parameter is null";
	public static final String CONST_BANK_ACCOUNT_NUMBER = "account_number";
	public static final String CONST_BANK_NAME = "name";
	public static final String CONST_BANK_ACCOUNT = "bank_account";
	public static final String CONST_BANK_IFSC = "ifsc";
	public static final String BANK_DETAILS_NULL = "Bank Details is null please create Bank Details";
	public static final String METHOD_CRE_PAY = "CREATE_PAYMENT";
	public static final String ERROR_WHILE_SAVE_CREATE_PAYMENT = "Error Occur While Saving Create Payment";

	public static final String VERIFY_SUCCEED = "Verified and updated successfully";
	public static final String VERIFY_NOT_SUCCEED = "Verify not succeed";
	public static final String CANNOT_GET_BANK_DETAILS = "Cannot get bank details";

	// ** Email **//
	public static final String PAYOUT_SUBJECT = "Pay out Failure : Chola";
	public static final String PAYMENT_SUBJECT = "Pay In Failure : Chola";

	// ** RAZORPAY **//
	public static final String RAZORPAY_NOTES = "notes";
	public static final String RAZORPAY_UPI = "upi";
	public static final String RAZORPAY_NET_BANKING = "netbanking";
	public static final String RAZORPAY_AMOUNT = "amount";
	public static final String RAZORPAY_CURRENCY = "currency";
	public static final String RAZORPAY_RECEIPT = "receipt";
	public static final String RAZORPAY_BANK_ACCOUNT = "bank_account";
	public static final String RAZORPAY_METHOD = "method";
	public static final String RAZORPAY_ACCOUNT_NUMBER = "account_number";
	public static final String RAZORPAY_ACCOUNT_NAME = "name";
	public static final String RAZORPAY_ACCOUNT_IFSC = "ifsc";
	public static final String RAZORPAY_CLIENT_CODE = "clientcode";
	public static final String RAZORPAY_ORDERID = "razorpay_order_id";
	public static final String RAZORPAY_PAYMENTID = "razorpay_payment_id";
	public static final String RAZORPAY_SIGNATURE = "razorpay_signature";

	// For Chola
	public static final String RAZORPAY_CLIENT_ID = "Client Id";
	public static final String RAZORPAY_PRODUCT = "Product";
	public static final String RAZORPAY_IFSC_CODE = "IFSC Code";
	public static final String RAZORPAY_BANK_NAME = "Bank Name";
	public static final String RAZORPAY_BANK_ACCOUNT_NUMBER = "Bank Account Number";

	public static final String LESS_THEN_MINIMUM = "Amount is less then the minimum Amount";
	public static final String PAYMENT = "PAYMENT";
	public static final String NEFT = "NEFT";
	public static final String TRANSFER = "TRANSFER";
	public static final String PAYOUT_CANCELLED = "Payout has been Cancelled";
	public static final String PAYMENT_STATUS_COMPLETED = "completed";
	public static final String FAILED_TO_GET_OWN_CODE = "Failed to get own code";
	public static final String FAILED_TO_GET_BANK_CODE = "Failed to get bank code";

	public static final String INVALID_PARAMETERS = "Invalid Parameters";
	public static final String NO_RECORDS_FOUND = "No Records Found";
	public static final String DAY_LIMIT_EXCEEDED = "Day Limit Exceeded";
	
	public static final String STRICTHOSTKEYCHECKING = "StrictHostKeyChecking";
	public static final String NO = "no";
	public static final String SFTP = "sftp";
	protected static final String MASTERDATAFAILURE = "Master Data Download Failure";
}
