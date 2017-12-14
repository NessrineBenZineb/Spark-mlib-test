package spark;

import static java.util.Collections.singletonList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.api.java.AbstractJavaRDDLike;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.execution.datasources.Partition;
import org.bson.Document;

import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.spark.MongoSpark;
import com.mongodb.spark.config.WriteConfig;
import com.mongodb.spark.rdd.api.java.JavaMongoRDD;
import com.mongodb.util.JSON;

public class TraitementSpark5 {
// exemple achat dentifrice
	public static String traitement(String i)
	{
		int id=Integer.parseInt(i);
		System.out.println("id user"+id);
		SparkSession spark = SparkSession.builder()
			      .master("local")
			      .appName("MongoSparkConnectorIntro")
			     
			      
			    	  .config("spark.mongodb.input.uri","mongodb://ds139436.mlab.com:39436/testdatabase.product")
			     // .config("spark.mongodb.output.uri", "mongodb://127.0.0.1/test.responseCollection")
			      .getOrCreate();

			    // Create a JavaSparkContext using the SparkSession's SparkContext object
			    JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());
			    
			    
			    /*Start Example: Read data from MongoDB************************/
			    JavaMongoRDD<Document> rdd = MongoSpark.load(jsc);
			   
			    System.out.println(rdd.count());
			    /*End Example**************************************************/
			   try{ JavaMongoRDD<Document> aggregatedRddUser = rdd.withPipeline(
			    	      singletonList(
			    	        Document.parse("{ $match: {id:"+id+" } }")));
			    Document jsono=aggregatedRddUser.first();
		
			  System.out.println("array "+jsono);
			    JavaMongoRDD<Document> aggregatedRdd = rdd.withPipeline(
			    	      singletonList(
			    	   mlib.get  (  Document.parse("{ $match: {achat: {  $in:"+ jsono.get("dentifrice")+"} } }")));

			   
			 			 
			    List<Document> listjson=aggregatedRdd.collect();
			    String listUsers="["+listjson.get(0).toJson();
			
			for(int j=1;j< listjson.size();j++)
			{
				listUsers =listUsers+","+listjson.get(j).toJson();
			}
			listUsers=listUsers+"]";
			jsc.close();
			 return listUsers; 
			   }
			   catch(Exception e){
				   System.out.println("error "+e.toString());
				   return e.toString();
			   }
			
			    
			    
			    
			 
					  
	}
}
