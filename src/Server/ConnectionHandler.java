/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nati
 */
class ConnectionHandler extends Thread {

    String title;
    String description;
    String path;
    DataInputStream inStream;
    DataOutputStream outStream;
    DataInputStream inStreamToFile;
    DataOutputStream outStreamToFile;
    
    DataInputStream indexIn;
    DataOutputStream indexOut;
    Socket socket;
    String extension;
    int teacher;
    public ConnectionHandler() {
    }

    public ConnectionHandler(Socket socket) {
        this.socket = socket;
    }
    int name;
    public void run() {
        try {
            
            loadName();
            
            
            //inStreamToFile = new DataInputStream(new FileInputStream("C:\\Users\\nati\\Desktop\\data\\nati.txt"));
            inStream = new DataInputStream(socket.getInputStream());
            outStream = new DataOutputStream(socket.getOutputStream());
            char user = inStream.readChar();
            
            if (user == '{') {
                int data;
                teacher=inStream.readInt();
                extension=inStream.readUTF();
                outStreamToFile = new DataOutputStream(new FileOutputStream("C:\\Users\\Nati\\Desktop\\Crap\\data\\"+name+"."+extension));
                title=inStream.readUTF();
                description=inStream.readUTF();
                
                while (true) {
                    data = inStream.readByte();
                    outStreamToFile.writeByte(data);
                }
                
            } else {

            }
        } catch (IOException ex) {
            System.out.println("Uploading Finished!");
            //register(teacher,name);
        } finally {
            System.out.println("Finished");
            
            
            try {
                saveName();
                outStreamToFile.close();
                inStream.close();
                inStream.close();
                outStream.close();
            } catch (Exception ex) {
                Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    public void register(String teacher,String name){
        try{
        Connection conn=DriverManager.getConnection(url,usr,pass);
        Statement stmnt=conn.createStatement();
        PreparedStatement ps=conn.prepareStatement("insert into file values(id=?,title=?,description=?path=?,teach_id=?)");
        //ps.setInt
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void loadName(){
        try {
            indexIn = new DataInputStream(new FileInputStream("C:\\Users\\Nati\\Desktop\\Crap\\data\\index.dat"));
            name=indexIn.readInt();
            indexIn.close();
        } catch (Exception ex) {
            Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void saveName() throws Exception{
                name+=1;
                indexOut = new DataOutputStream(new FileOutputStream("C:\\Users\\Nati\\Desktop\\Crap\\data\\index.dat"));
                indexOut.writeInt(name);
                indexOut.close();
    }
    public String generateName() {
        return Integer.toString(name++);
    }
    Connection conn=null;
    Statement stmnt=null;
    ResultSet rs=null;
    String url="jdbc:mysql://localhost/student_management";
    String usr="root";
    String pass="root";
    public void register(){
        try{
                conn=DriverManager.getConnection(url,usr,pass);
                stmnt=conn.createStatement();
                stmnt.executeUpdate("insert in to file values()");
                boolean flag=false;
                
                
            }catch(SQLException e){
                e.printStackTrace();
            }finally{
                        try{
                            conn.close();
                            stmnt.close();
                            rs.close();
                        }catch(Exception ex){
                            ex.printStackTrace();
                        }
                    } // TODO add your handling code here:
    }

}
