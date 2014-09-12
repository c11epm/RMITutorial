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
package src.Server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import src.resourses.AliveCheck;
import src.resourses.BindItem;
import src.resourses.Hello;
import src.resourses.PropertyReader;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Hello, AliveCheck, BindItem{
    static final Logger logger = LogManager.getLogger(Server.class.getName());
    private static int defaultPort = 2002;
    private static String output;
    private static Registry registry;
    public Server() {
    }

    public String sayHello() throws RemoteException /*throws RemoteException*/ {
        return "Hello, World!";
    }

    @Override
    public boolean bindToMe(String remoteName, AliveCheck toBind) throws RemoteException {
        registry.rebind(remoteName, toBind);
        return true;
    }

    public static void main(String args[]) {
        output = (args.length < 1) ? "Hello, World!" : args[0];
        String host = "";
        try {
            defaultPort = Integer.parseInt(PropertyReader.getInstance().readRegistryProperties("registry.properties", "port"));
            host = PropertyReader.getInstance().readRegistryProperties("registry.properties", "host");
        } catch (IOException e) {
            logger.error("Error: " + e);
        }
        logger.info("Sever starting on port " + defaultPort + "... ");
        try {
            Server obj = new Server();
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            registry = LocateRegistry.getRegistry(host, defaultPort);
            logger.info("Got registry on port: " + defaultPort);
            String nameToBind = PropertyReader.getInstance().readMessageProperties("message.properties", "hello");

            //TODO: Fix rebind stuff. Done
            registry.rebind(nameToBind, stub);

            logger.info("Server ready on port: " + defaultPort);
        } catch (Exception e) {

            logger.error("Error: " + e.getMessage());
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();

        }
    }

    @Override
    public boolean isAlive() {
        return true;
    }
}
