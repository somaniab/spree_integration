import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;


public class ProductQuery
{
	public static void main(String[] args) throws IOException
	{
		
		File dir =new File("E:\\MK\\newImages");
		
		File output = new File("E:\\MK\\aspl");
		
		String q = "insert into spree_products (id,`name`,description,available_on,slug,meta_description,meta_keywords,shipping_category_id,created_at,updated_at) values(";
		
		String q2 = "insert into spree_product_option_types (id,position,product_id,option_type_id,created_at,updated_at) values(";
		
		String q3 = "insert into spree_variants (id,sku,is_master,product_id,position,updated_at,cost_currency) values(";
		
		String q4 = "insert into spree_option_values_variants(variant_id,option_value_id) values(";
		
		String q5 = "insert into spree_option_values(id,`name`,presentation,position,option_type_id,created_at,updated_at) values(";
		
		String q6 = "insert into spree_option_types(id,`name`,presentation,position,created_at,updated_at) values(";
		
		String q7 = "insert into spree_prices(id,variant_id,amount,currency) values(";
		
		String q8 = "insert into spree_taxonomies(id,name,created_at,updated_at) values(";
		
		String q9 = "insert into spree_taxons(id,position,name,permalink,taxonomy_id,lft,rgt,created_at,updated_at) values(";
		
		String q10 = "insert into spree_products_taxons(product_id,taxon_id,id,position) values(";
		
		String q11 ="INSERT INTO `spree_assets`(`id`, `viewable_id`, `viewable_type`, `attachment_width`, `attachment_height`, `attachment_file_size`, `position`, `attachment_content_type`, `attachment_file_name`, `type`, `attachment_updated_at`, `alt`, `created_at`, `updated_at`) values (";
	
		String q12 ="INSERT INTO  `spree_stock_items` (`id` ,`stock_location_id`,`variant_id`,`count_on_hand`,`created_at`,`updated_at`,`backorderable`)values( ";
		
		String d = "delete from spree_products where id=";
		
		String d2 = "delete from spree_product_option_types where id=";
		
		String d3 = "delete from spree_variants where id=";
		
		String d4 = "delete from spree_option_values_variants where variant_id=";
		
		String d5 = "delete from spree_option_values where id=";
		
		String d6 = "delete from spree_option_types where id=";
		
		String d7 = "delete from spree_prices where id=";
		
		String d8 = "delete from spree_taxonomies where id=";
		
		String d9 = "delete from spree_taxons where id=";
		
		String d10 = "delete from spree_products_taxons where id=";
		
		String d11 = "delete from spree_assets where id=";
		
		String d12 = "delete from spree_stock_items where id=";
		
		StringBuilder deletMaster = new StringBuilder();
		
		
		String shipping_id="1";
		
		String option_type_id="100";//fabric length
		
		String option_value_id="101,102,103,104,105,106";//comma separated list of fabric length
		
		int taxonomy_id = 100;
		int taxons_id=100;
		String taxonomyName = "Suitings";
		String taxonomylink = "suitings";
		
		StringBuilder master = new StringBuilder();
		
		
		
		master.append(q8);
		master.append(taxonomy_id);
		master.append(",'");
		master.append(taxonomyName);
		master.append("',");
		master.append("now()");
		master.append(",");
		master.append("now());");
		master.append("\r\n");
		
		deletMaster.append(d8);
		deletMaster.append(taxonomy_id+";");
		deletMaster.append("\r\n");
		
		
		
		master.append(q9);
		master.append(taxons_id);
		master.append(",1,'"+taxonomyName+"','"+taxonomylink+"',"+taxonomy_id);
		master.append(","+"9,10,"+"now(),now());");
		master.append("\r\n");
		
		deletMaster.append(d9);
		deletMaster.append(taxons_id+";");
		deletMaster.append("\r\n");
		
		master.append(q6);
		master.append(option_type_id);
		master.append(",'");
		master.append("Fabric-Length'");
		master.append(",'");
		master.append("Fabric-Length");
		master.append("',");
		master.append("1");
		master.append(",");
		master.append("now()");
		master.append(",");
		master.append("now());");
		master.append("\r\n");
		
		deletMaster.append(d6);
		deletMaster.append(option_type_id+";");
		deletMaster.append("\r\n");
		
		String[] option_value_names = new String[]{"1.20 Meter","1.30 Meter","1.60 Meter","2.30 Meter","2.60 Meter","3.00 Meter"};
		
		String arr[] = option_value_id.split(",");
		int count=0;
		for(String s : arr)
		{
			master.append(q5);
			master.append(s);
			master.append(",'");
			master.append(option_value_names[count]);
			master.append("','");
			master.append(option_value_names[count]);
			count++;
			master.append("',");
			master.append(count);
			master.append(",");
			master.append(option_type_id);
			master.append(",");
			master.append("now()");
			master.append(",");
			master.append("now());");
			master.append("\r\n");
			
			deletMaster.append(d5);
			deletMaster.append(s+";");
			deletMaster.append("\r\n");
		}
		count=0;
		int product_option_type_count =100;
		int spree_variant_count =100;
		int spree_prices_count=100;
		int product_taxon_id=100;
		//int asset_id=100;
		int spree_stock_count=100;
		String currency="INR";
		
		List<String> names = createImages(dir,output);
		System.out.println(names);
		for(String name :names )
		{
			int amount=250;
			count=0;
			String[] var = name.split(",",-1);
			String pId = var[0];
			String pname = var[0]+"-"+var[1].substring(0,var[1].indexOf("."));
			int width = Integer.parseInt(var[2]);
			int height = Integer.parseInt(var[3]);
			int contentSize = Integer.parseInt(var[4]);
			
			
			//product
			master.append(q);
			master.append(pId);
			master.append(",'");
			master.append(pname);
			master.append("','");
			master.append(pname.replace("-"," ") + "- Premium Suiting Fabric By Ashutosh");
			master.append("',now(),'");
			master.append(pname);
			master.append("','"+pname+"'");
			master.append(",'"+pname+",suiting ,export quality , premium fabric'");
			master.append(","+shipping_id);
			master.append(","+"now(),now());");
			master.append("\r\n");
			
			deletMaster.append(d);
			deletMaster.append(pId+";");
			deletMaster.append("\r\n");
			//product option type
			master.append(q2);
			master.append(product_option_type_count);
			master.append(",1,"+pId+","+option_type_id+",now(),now());");
			master.append("\r\n");
			
			deletMaster.append(d2);
			deletMaster.append(product_option_type_count+";");
			deletMaster.append("\r\n");
			product_option_type_count++;
			
			
			//taxons
			master.append(q10);
			master.append(pId);
			master.append(","+taxons_id+","+product_taxon_id+",1);");
			master.append("\r\n");
			
			deletMaster.append(d10);
			deletMaster.append(product_taxon_id+";");
			deletMaster.append("\r\n");
			product_taxon_id++;
			//variants
			String ASPL ="ASPL";
			int isMaser = 1 ;
			for(String s : arr)
			{
				
				master.append(q3);
				master.append(spree_variant_count);
				
				master.append(",'"+ASPL+spree_variant_count+"'");
				master.append(","+isMaser);
				master.append(","+pId);
				master.append(",1");
				master.append(",now(),'INR');");
				master.append("\r\n");
				
				deletMaster.append(d3);
				deletMaster.append(spree_variant_count+";");
				deletMaster.append("\r\n");
				
				//stock
				master.append(q12);
				master.append(spree_stock_count);
				
				master.append(",1,"+spree_variant_count+",10");
				master.append(",now(),now(),1);");
				master.append("\r\n");
				
				deletMaster.append(d12);
				deletMaster.append(spree_stock_count+";");
				deletMaster.append("\r\n");
				spree_stock_count++;
				
				//assets 
				if(isMaser !=0)
				{
					master.append(q11);
					master.append(pId);
					master.append(","+spree_variant_count);
					master.append(",'Spree::Variant',"+width+","+height+","+contentSize);
					master.append(","+1);
					master.append(",'image/jpeg'");
					master.append(",'"+var[1]+"','Spree::Image',now(),'Premium Suiting',now(),now());");
					master.append("\r\n");
					
					deletMaster.append(d11);
					deletMaster.append(pId+";");
					deletMaster.append("\r\n");
					
					//asset_id++;
					
				}
				
				
				//prices
				master.append(q7);
				master.append(spree_prices_count);
				master.append(","+spree_variant_count);
				master.append(","+amount);
				master.append(",'"+currency+"');");
				master.append("\r\n");
				
				deletMaster.append(d7);
				deletMaster.append(spree_prices_count+";");
				deletMaster.append("\r\n");
				spree_prices_count++;
				amount=amount+50;
				
				
				master.append(q4);
				master.append(spree_variant_count);
				master.append(","+s+");");
				master.append("\r\n");
				
				deletMaster.append(d4);
				deletMaster.append(spree_variant_count+";");
				deletMaster.append("\r\n");
				
				isMaser=0;
				spree_variant_count++;
				count++;
			}
		}
		
		IOUtils.write(master.toString(),new FileOutputStream(new File(output,"export.sql")));
		IOUtils.write(deletMaster.toString(),new FileOutputStream(new File(output,"cleanUp.sql")));
				
	}
	
	public static List<String> createImages(File inputDir , File outDir) throws IOException
	{
		List<String> fileNames = new LinkedList<String>();
		long startTime = System.currentTimeMillis();
		
		File[] arr = inputDir.listFiles();
		for(File d : arr)
		{
			StringBuilder name = new StringBuilder();
			name.append(d.getName()+",");
			File[] arr2 = d.listFiles();
			for(File inputimg : arr2)
			{
				
				name.append(inputimg.getName()+",");
				
				
				BufferedImage img = ImageIO.read(inputimg); // load image
				
				FileInputStream in = new FileInputStream(inputimg);
				name.append(img.getWidth()+","+img.getHeight()+","+IOUtils.toByteArray(in).length);
				in.close();
				
				File outDir2 = new File(outDir ,d.getName());
				outDir2.mkdir();
				
				//large
				File largeDir = new File(outDir2,"large");
				largeDir.mkdir();
				File largeImg = new File(largeDir , inputimg.getName());
				copy(inputimg ,largeImg);
				
				//mini
				File miniDir = new File(outDir2,"mini");
				miniDir.mkdir();
				File miniImgf = new File(miniDir , inputimg.getName());
				
				BufferedImage miniImg = Scalr.resize(img, Method.QUALITY,Mode.AUTOMATIC, 
			             48,48, Scalr.OP_ANTIALIAS);
				 ImageIO.write(miniImg, "jpg", miniImgf);
				
				 //original
				File origDir = new File(outDir2,"original");
				origDir.mkdir();
				File origImg = new File(origDir , inputimg.getName());
				copy(inputimg ,origImg);
					
				//product
				File prodDir = new File(outDir2,"product");
				prodDir.mkdir();
				File prodImgf = new File(prodDir , inputimg.getName());
				img = ImageIO.read(inputimg); // load image
				BufferedImage prodImg = Scalr.resize(img, Method.QUALITY,Mode.AUTOMATIC, 
			             240,240, Scalr.OP_ANTIALIAS);
				 ImageIO.write(prodImg, "jpg", prodImgf);
				 
				//small
					File smallDir = new File(outDir2,"small");
					smallDir.mkdir();
					File smallImgf = new File(smallDir , inputimg.getName());
					img = ImageIO.read(inputimg); // load image
					BufferedImage smallImg = Scalr.resize(img, Method.QUALITY,Mode.AUTOMATIC, 
				             100,100, Scalr.OP_ANTIALIAS);
					 ImageIO.write(smallImg, "jpg", smallImgf);
			}
			fileNames.add(name.toString());
		}
		return fileNames ;
	}

	private static void copy(File inputimg, File largeImg) throws IOException
	{
		FileInputStream in = new FileInputStream(inputimg);
		FileOutputStream out = new FileOutputStream(largeImg);
		IOUtils.copy(in,out);
		in.close();
		out.close();
	}
}
