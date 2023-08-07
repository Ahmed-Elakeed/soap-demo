package com.webservices.soapdemo.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.XsdSchemaCollection;
import org.springframework.xml.xsd.commons.CommonsXsdSchemaCollection;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "countries")
    public DefaultWsdl11Definition countriesSoapDefinition() {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CountriesPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://country.soap.soapdemo.webservices.com/");
        wsdl11Definition.setSchema(countriesSchema());
        return wsdl11Definition;
    }

    @Bean(name = "citizens")
    public DefaultWsdl11Definition citizensSoapDefinition() {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CitizensPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://citizen.soap.soapdemo.webservices.com/");
        wsdl11Definition.setSchema(citizensSchema());
        return wsdl11Definition;
    }

    @Bean(name = "all-soap")
    public DefaultWsdl11Definition defaultWsdl11Definition() {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("AllPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://soap.soapdemo.webservices.com/");
        wsdl11Definition.setSchemaCollection(getXsdCollection());
        return wsdl11Definition;
    }


    @Bean
    public XsdSchemaCollection getXsdCollection() {
        CommonsXsdSchemaCollection xsds = new CommonsXsdSchemaCollection(new ClassPathResource("/wsdl/citizens.xsd"),
                new ClassPathResource("/wsdl/countries.xsd"));
        xsds.setInline(true);
        return xsds;
    }

    @Bean
    public XsdSchema countriesSchema(){
        return new SimpleXsdSchema(new ClassPathResource("wsdl/countries.xsd"));
    }

    @Bean
    public XsdSchema citizensSchema(){
        return new SimpleXsdSchema(new ClassPathResource("wsdl/citizens.xsd"));
    }
}
