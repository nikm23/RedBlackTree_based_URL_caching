package crawler;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
 
public class Main {
	public static DB db = new DB();
        public static RBTree rbt;
 
	public static void main(String[] args) throws SQLException, IOException {
		db.runSql2("TRUNCATE Record;");
                rbt = new RBTree("a");
                //rbt.insert("http://www.mit.edu/");
		processPage("http://www.thapar.edu/");
	}
 
	public static void processPage(String URL) throws SQLException, IOException{
            /*
                if(rbt.search(URL)){
                    System.out.println("URL: " + URL + " already present.");
                }*/
		//check if the given URL is already in database
		//String sql = "select * from Record where URL = '"+URL+"'";
		//ResultSet rs = db.runSql(sql);
                if(rbt.search(URL)){
                    
                }else{
			//store the URL to database to avoid parsing again      
			String sql = "INSERT INTO  `Crawler`.`Record` " + "(`URL`) VALUES " + "(?);";
			PreparedStatement stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, URL);
			stmt.execute();
 
			//get useful information
			Document doc = Jsoup.connect("http://www.thapar.edu/").get();
 
			if(doc.text().contains("research")){      
				System.out.println(URL);
			}
 
			//get all links and recursively call the processPage method
			Elements questions = doc.select("a[href]");
			for(Element link: questions){    
				if(link.attr("href").contains("www.thapar.edu"));
                                rbt.insert(link.attr("abs:href"));
                                        processPage(link.attr("abs:href"));
                                    
			}
		}
	}
}