package viewSDB;


import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDB;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.Attribute;
import com.amazonaws.services.simpledb.model.BatchPutAttributesRequest;
import com.amazonaws.services.simpledb.model.CreateDomainRequest;
import com.amazonaws.services.simpledb.model.DeleteAttributesRequest;
import com.amazonaws.services.simpledb.model.DeleteDomainRequest;
import com.amazonaws.services.simpledb.model.Item;
import com.amazonaws.services.simpledb.model.PutAttributesRequest;
import com.amazonaws.services.simpledb.model.ReplaceableAttribute;
import com.amazonaws.services.simpledb.model.ReplaceableItem;
import com.amazonaws.services.simpledb.model.SelectRequest;
import com.amazonaws.services.simpledb.model.SelectResult;

public class viewSDB {
	
	public static void main(String[] args) throws Exception {
		String secretKey = "ctFMvj+c1Jmh9tHBi0zaTImmxL1GQIIAdWiBXrVx";
		   String accessKey = "AKIAINULFTWQFFTCD2EQ";
		   BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
		   AmazonSimpleDB sdb = new AmazonSimpleDBClient(basicAWSCredentials);
		   
		// Create a domain
           String domain = "projectDomain";
           sdb.createDomain(new CreateDomainRequest(domain));
           
           // List domains
           System.out.println("Listing all domains in your account:\n");
           for (String domainName : sdb.listDomains().getDomainNames()) {
               System.out.println("  " + domainName);
           }
           System.out.println();
  
           
           // Select data from a domain
           // Notice the use of backticks around the domain name in our select expression.
           String selectExpression = "select * from " + domain;
           System.out.println("Selecting: " + selectExpression + "\n");
           SelectRequest selectRequest = new SelectRequest(selectExpression);
           for (Item item : sdb.select(selectRequest).getItems()) {
               System.out.println("  Item");
               System.out.println("    Name: " + item.getName());
               for (Attribute attribute : item.getAttributes()) {
                   System.out.println("      Attribute");
                   System.out.println("        Name:  " + attribute.getName());
                   System.out.println("        Value: " + attribute.getValue());
               }
           }
	}
}