package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hpsf.Array;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

public class ReadGoogleSheetData {
	private static Sheets sheetService;
	private static String ApplicationName = "Reading  data from google sheet";
	private static String SPREADSHEET_ID = "1c4t-2ldiL7x_byaos6t1IuyI75ZTdJN9mkWneXF3unA";
	
	private static  Credential authorize() throws IOException, GeneralSecurityException
	{
		InputStream in = ReadGoogleSheetData.class.getResourceAsStream("/credentials.json");
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new InputStreamReader(in));
		
		List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);
		
	GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), clientSecrets, scopes).setDataStoreFactory(new FileDataStoreFactory(new java.io.File(pathname: "tokens"))).setAccessType("offline").build();
	
	Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
	return credential;

	}
	
	public static Sheets getSheetsService() throws IOException, GeneralSecurityException {
        Credential credential = authorize();
        return new Sheets.Builder(
          GoogleNetHttpTransport.newTrustedTransport(), 
          JacksonFactory.getDefaultInstance(), credential)
          .setApplicationName(ApplicationName)
          .build();
    }
	
	
   public static void main(String args[]) throws IOException, GeneralSecurityException {
	   sheetService= getSheetsService();
	   String range = "Automation_TestData!A2:D12";
	   
	   ValueRange response = sheetService.spreadsheets().values().get(SPREADSHEET_ID, range).execute();
	   
	   List<List<Object>> values = response.getValues();
	   if(values == null || values.isEmpty()) 
	   {
		  System.out.print("Not data found");
		  
	   }
	   else
	   {
		   for (List row : values) {
			   System.out.printf("Values are %s %s %s", row.get(1), row.get(2), row.get(5));
		   }
	   }
	   }
	   
	   
   }

