import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class CountriesQury
{
	public static void main(String[] args) throws IOException
	{
		String q = "insert into spree_states (id,name,abbr,country_id,updated_at) values(";
		
		File f = new File("C:\\Users\\ACSMAC05\\git\\integration\\countries.txt");
		
		BufferedReader br = new BufferedReader(new FileReader(f));
		String s =null;
		while((s=br.readLine())!=null)
		{
			String arr[] = s.split(",");
			StringBuilder newQ =new StringBuilder(q) ;
			newQ.append(arr[0].trim());
			newQ.append(",");
			
			String state = arr[1].substring(0,arr[1].lastIndexOf(" "));
			String abbr = arr[1].substring(arr[1].lastIndexOf(" ")+1);
			
			newQ.append(state.trim());
			newQ.append("',");
			newQ.append("'"+abbr.trim());
			newQ.append(",");
			newQ.append(1);
			newQ.append(",");
			newQ.append("now());");
			System.out.println(newQ.toString());
		}
		
	}
}
