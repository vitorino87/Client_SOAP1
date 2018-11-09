/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_soap1;

import java.net.URL;
import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

/**
 *
 * @author tiago.lucas
 */
public class Client_SOAP1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            SOAPConnectionFactory soapFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection connection = soapFactory.createConnection();
            
            MessageFactory factory = MessageFactory.newInstance();
            SOAPMessage message = factory.createMessage();
            
            SOAPHeader header = message.getSOAPHeader();
            SOAPBody body = message.getSOAPBody();
            header.detachNode();
            
            QName bodyName = new QName("http://soap/","outroHello","m");
            SOAPBodyElement bodyElement = body.addBodyElement(bodyName);
            
            QName name = new QName("valor1");
            SOAPElement nome = bodyElement.addChildElement(name);
            nome.addTextNode("Tiago Vitorino");           
            
            //URL endpoint = new URL("https://localhost:8181/SOAP1/SOAP1?wsdl");
            //URL endpoint = new URL("https://ti.int");
            URL endpoint = new URL("http://ti.int:8080/SOAP1/SOAP1");
            SOAPMessage response = connection.call(message, endpoint);                        
            
            connection.close();
            
            SOAPBody soapBody = response.getSOAPBody();
            
            Iterator iterator = soapBody.getChildElements();
            bodyElement = (SOAPBodyElement) iterator.next();
            String resposta = bodyElement.getTextContent();
            
            System.out.println(resposta);                                    
        }catch(Exception ex){
            ex.printStackTrace();            
        }
    }
    
}
