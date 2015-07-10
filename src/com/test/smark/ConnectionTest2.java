package com.test.smark;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.util.Scanner;

/**
 * Created by zhangfan on 2015/7/1.
 */
public class ConnectionTest2 {


    public static void main(String[] args) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    ConnectionTest2 connectionTest = new ConnectionTest2();
                    connectionTest.smack();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(50 * 1000);
    }


    public static void smack() throws Exception {

        SmackConfiguration.DEBUG = true;
        XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                .setServiceName("openfirefan")
                .setHost("zhangfan.com")
                .setPort(5222)
                .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                .build();

        AbstractXMPPConnection conn2 = new XMPPTCPConnection(config);
        conn2.connect();
        System.out.println(conn2.isConnected());

        //添加用户
      /*  AccountManager accountManager = AccountManager.getInstance(conn2);

        accountManager.sensitiveOperationOverInsecureConnection(true);
        accountManager.createAccount("lotusfan1", "123456789");*/


        //组
      /*  Roster roster = Roster.getInstanceFor(conn2);
        Collection<RosterEntry> entries = roster.getEntries();
        for (RosterEntry entry : entries) {
            System.out.println(entry);
        }*/

        //发送消息
        // Most servers require you to login before performing other tasks.
        conn2.login("lotusfan", "123456789");
        // Start a new conversation with John Doe and send him a message.

        Roster roster = Roster.getInstanceFor(conn2);
        roster.setSubscriptionMode(Roster.SubscriptionMode.accept_all);
        roster.createEntry("zhangfan@openfire", "zhangfan", null);




        ChatManager chatManager = ChatManager.getInstanceFor(conn2);
        chatManager.addChatListener(new ChatManagerListener() {
            @Override
            public void chatCreated(Chat chat, boolean b) {
                chat.addMessageListener(new ChatMessageListener() {
                    @Override
                    public void processMessage(Chat chat, Message message) {
                        System.out.println("chatManager:" + chat.getThreadID());
                        System.out.println("chatManager" + message.getBody());
                    }
                });
            }
        });


        Chat chat = chatManager.createChat("zhangfan@openfirefan", new ChatMessageListener() {
            @Override
            public void processMessage(Chat chat, Message message) {
                System.out.println("chat:" + chat.getThreadID());
                System.out.println("chat:" + message.getBody());
            }
        });
        System.out.println(chat.getThreadID());
        Thread.sleep(3 * 1000);
        System.out.println("\n");
        chat.sendMessage("nihao zhangfan");
        while (true) {

            Scanner scanner = new Scanner(System.in);
            String str = scanner.next();
            chat.sendMessage(str);
        }
        // Disconnect from the server
    }


}
