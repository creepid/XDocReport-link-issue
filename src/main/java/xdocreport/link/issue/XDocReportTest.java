package xdocreport.link.issue;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author rusakovich
 */
public class XDocReportTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         try
        {
            // 1) Load Docx file by filling Velocity template engine and cache
            // it to the registry
            InputStream in = XDocReportTest.class.getResourceAsStream( "DocxProjectWithVelocity.docx" );
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport( in, TemplateEngineKind.Velocity );

            // 2) Create context Java model
            IContext context = report.createContext();
            
            Project project = new Project();
            project.setLink("project link");
            
            context.put("project", project );

            // 3) Generate report by merging Java model with the Docx
            OutputStream out = new FileOutputStream( new File( "DocxProjectWithVelocity_Out.docx" ) );
            report.process( context, out );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
        catch ( XDocReportException e )
        {
            e.printStackTrace();
        }
    }
    
}
