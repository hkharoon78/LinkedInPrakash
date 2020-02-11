package utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;




public class PropertiesFile
{
  public static File nf;
  
  public PropertiesFile() {}
  
  public static final ArrayList<String> testCases = new ArrayList();
  public static HashMap<String, String> category = new HashMap<String, String>();
  
  public static void properties() {
    Properties prop = new Properties();
    try
    {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = dbf.newDocumentBuilder();
      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("suite");
      doc.appendChild(rootElement);
      Element test = doc.createElement("test");
      Element classes = doc.createElement("classes");
      Element classs = doc.createElement("class");
      Element methods = doc.createElement("methods");
      rootElement.appendChild(test);
      test.appendChild(classes);
      classes.appendChild(classs);
      classes.appendChild(methods);
      
      rootElement.setAttribute("name", "Suite");
      rootElement.setAttribute("parallel", "none");
      rootElement.setAttribute("configfailurepolicy", "continue");
      test.setAttribute("name", "Test");
      test.setAttribute("preserve-order", "true");
      classs.setAttribute("name", "TestCases.TestCases");
      TransformerFactory tff = TransformerFactory.newInstance();
      ExcelData excelData = new ExcelData("/home/haroon/Documents/gopi/v5/source code/LinkedInPrakashv5/LinkedInPrakash/src/main/java/utilities/data.xlsx", "Modules");
    
      for (int i = 1; i < excelData.getRowCount(); i++)
      {
    	  String value = "";
    	  try {
        value = excelData.getData(i, 3);
    	  }catch(Exception e) {
    		  
    	  }
        if (value.trim().equalsIgnoreCase("Yes"))
        {
          Element include = doc.createElement("include");
          methods.appendChild(include);
          Keyword = excelData.getData(i, 2).toString().replace(" ", "");
          testCases.add(Keyword);
          include.setAttribute("name", Keyword);
        } else if (value.trim().equalsIgnoreCase("No")) {
          Element exclude = doc.createElement("exclude");
          methods.appendChild(exclude);
          String Keyword = excelData.getData(i, 2).toString();
          exclude.setAttribute("name", Keyword);
        }
        else if (!value.trim().equalsIgnoreCase(""))
        {
          System.out.println("Warnin!!Invalid/Empty Execution flag");
        }
      }
      
      File file = new File("C:\\Apps\\temp");
      file.mkdirs();

      Transformer transformer = tff.newTransformer();
      transformer.setOutputProperty("indent", "yes");
      transformer.setOutputProperty(
        "{http://xml.apache.org/xslt}indent-amount", "2");
      DOMSource xmlSource = new DOMSource(doc);
      StreamResult outputTarget = new StreamResult(
        "C:\\Apps\\temp\\testng.xml");
      transformer.transform(xmlSource, outputTarget);
     } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static String Keyword;

}
