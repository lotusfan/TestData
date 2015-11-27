package com.test.smark;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.roster.packet.RosterPacket;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.iqregister.AccountManager;

import java.util.Collection;

/**
 * Created by zhangfan on 2015/7/1.
 */
public class ConnectionTest {


    public static void main(String[] args) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    ConnectionTest connectionTest = new ConnectionTest();
                    connectionTest.smack();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(50 * 1000);
    }


    public static void smack() throws Exception {

//        SmackConfiguration.DEBUG = true;
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
//        conn2.login("lotusfan1", "123456789");
        conn2.login("zhangfan", "123456");
        // Start a new conversation with John Doe and send him a message.
        Thread.sleep(5000);

        ChatManager chatManager = ChatManager.getInstanceFor(conn2);

        //发送消息
        /*chatManager.addChatListener(new ChatManagerListener() {
            @Override
            public void chatCreated(Chat chat, boolean b) {
                chat.addMessageListener(new ChatMessageListener() {
                    @Override
                    public void processMessage(Chat chat, Message message) {
                        System.out.println(chat.getParticipant());
                        System.out.println("chatManager:" + chat.getThreadID());
                        System.out.println("chatManager：" + message.getBody());
                    }
                });
            }
        });

        Chat chat = chatManager.createChat("lotusfan@openfirefan", new ChatMessageListener() {
            @Override
            public void processMessage(Chat chat, Message message) {
                System.out.println("chat:" + chat.getThreadID());
                System.out.println("chat:" + message.getBody());
            }
        });
        System.out.println("\n");
        System.out.println(chat.getListeners().size());
        chat.sendMessage("nihao lotusfan");
        System.out.println(chat.getThreadID());*/

        //添加好友
        Roster roster = Roster.getInstanceFor(conn2);

//        roster.createEntry("lotusfan@openfire", "lotusfan", null);


        Collection<RosterEntry> entries = roster.getEntries();
        System.out.println(roster.getEntryCount());
        for (RosterEntry entry : entries) {
            System.out.println(entry);
        }


        // Disconnect from the server
    }


}
