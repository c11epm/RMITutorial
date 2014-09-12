/*
 * Copyright (c) 2004, Oracle and/or its affiliates. All rights reserved.
 *  
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met:
 * 
 * -Redistributions of source code must retain the above copyright  
 *  notice, this list of conditions and the following disclaimer.
 * 
 * -Redistribution in binary form must reproduce the above copyright 
 *  notice, this list of conditions and the following disclaimer in 
 *  the documentation and/or other materials provided with the 
 *  distribution.
 *  
 * Neither the name of Oracle or the names of 
 * contributors may be used to endorse or promote products derived 
 * from this software without specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 * EXCLUDED. SUN MICROSYSTEMS, INC. ("SUN") AND ITS LICENSORS SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR
 * ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT,
 * SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF
 * THE USE OF OR INABILITY TO USE THIS SOFTWARE, EVEN IF SUN HAS BEEN
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *  
 * You acknowledge that Software is not designed, licensed or 
 * intended for use in the design, construction, operation or 
 * maintenance of any nuclear facility.
 */
package src.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import src.resourses.Hello;
import src.resourses.PropertyReader;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    static final Logger logger = LogManager.getLogger(Client.class.getName());
    private static int defaultPort = 2003;
    private Client() {
    }

    public static void main(String[] args) {
        int argLength = args.length;
        Registry registry = null;
        int port = 0;
        String host = null;
        try {
            port = Integer.parseInt(PropertyReader.getInstance().readRegistryProperties("registry.properties", "port"));
            host = PropertyReader.getInstance().readRegistryProperties("registry.properties", "host");
        } catch (IOException e) {
            logger.error("Client error: " + e.getMessage());
            e.printStackTrace();
        }
        //String host = (argLength < 1) ? "localhost" : args[0];
        //int port = (argLength < 2) ? defaultPort : Integer.parseInt(args[1]);
        try {
            String messageName = PropertyReader.getInstance().readMessageProperties("message.properties", "hello");
            registry = LocateRegistry.getRegistry(host, port);
            String[] list = registry.list();
            for(String s : list){
                logger.info("before lookup: " + s);
                Hello stub = (Hello) registry.lookup(s);
                logger.info("After lookup, before sayHello");
                String response = stub.sayHello();
                logger.info("After sayHello");
                // registry.unbind("Hello");
                System.out.println("response: " + response);
            }

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
