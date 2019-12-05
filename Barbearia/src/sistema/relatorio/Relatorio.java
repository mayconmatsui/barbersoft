/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.relatorio;

import java.io.InputStream;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import sistema.model.Cliente;
import sistema.model.Produto;

/**
 *
 * @author ezequiel
 */
public class Relatorio {
    
    public void gerarRelatorioAniversariantesMes(List<Cliente> lista) throws JRException{
        InputStream is = Relatorio.class.getResourceAsStream("/sistema/relatorio/report/clienteAniversariantes.jrxml");
        JasperReport jr = JasperCompileManager.compileReport(is);
        JasperPrint print = JasperFillManager.fillReport(jr, null, new JRBeanCollectionDataSource(lista)); 
        JasperViewer.viewReport(print, false);

    }
    
    public void gerarRelatorioProdutos(List<Produto> lista) throws JRException{
        InputStream is = getClass().getResourceAsStream("/sistema/relatorio/report/produtos.jrxml");
        JasperReport jr = JasperCompileManager.compileReport(is);
        JasperPrint print = JasperFillManager.fillReport(jr, null, new JRBeanCollectionDataSource(lista)); 
        JasperViewer.viewReport(print, false);
    }
    
}
