import java.awt.event.*;

import javax.swing.JOptionPane;

public class Action extends Frame{
    @Override
    public void actionPerformed(ActionEvent Clicked){
        
        //Login Admin
        if(Clicked.getSource() == LoginButton[0]){
            String ID = LoginID.getText();
            String PW = String.valueOf(LoginPW.getPassword());
    
            if(ID.matches(AdminUser) && PW.matches(AdminPW)){
                Loginframe.dispose();
                AdminFrame.setVisible(true);
            }

            else{
                JOptionPane.showMessageDialog(null, "Invalid ID and/or Password", "Error Login", 0);
            }

        }

        //Login Member
        if(Clicked.getSource() == LoginButton[1]){
            String ID = LoginID.getText();
            String PW = String.valueOf(LoginPW.getPassword());
            boolean noMatch = true;

            for(int i = 0; i < memberCount; i++){
                if(ID.matches(MemberID[i]) && PW.matches(MemberPW[i])){
                    JOptionPane.showMessageDialog(null, "Login Success", "Error Login", 0);
                    MemberLabel[4].setText(MemberSN[i] + ", " + MemberFN[i] + " " + MemberMI[i] + ".");
                    MemberLabel[5].setText(MemberAddress[i]);
                    MemberFrame.setVisible(true);
                    Loginframe.dispose();
                    noMatch = false;
                    return;
                }
            }
            if(noMatch){
                JOptionPane.showMessageDialog(null, "Invalid ID and/or Password", "Error Login", 0);
            }
        }
        
        //Member Registration
        if(Clicked.getSource() == AdminButton[0]){
            RegFrame.setVisible(true);
        }
        if(Clicked.getSource() == RegButton[0]){
            int key6 = (int)Math.floor(Math.random() * 100000) + 100000;
            int key1 = (int)Math.floor(Math.random() * 9) + 1;
            
            String uniquekey = "ICTS-" + key6 + "-" + key1;
            RegField[1].setText(uniquekey);

            //Disable and Hide Generate Button. Expand Submit Button
            RegButton[0].setEnabled(false);
            RegButton[0].setVisible(false);
            RegButton[1].setBounds(10, 470, 480, 50);
        }
        if(Clicked.getSource() == RegButton[1]){
            String fname = RegName[0].getText();
            String sname = RegName[1].getText();
            String mname = RegName[2].getText();
            String address = RegField[0].getText();
            String ICST_ID = RegField[1].getText();
            String password = String.valueOf(RegPW[0].getPassword());
            String r_password = String.valueOf(RegPW[1].getPassword());
            boolean noInput = fname.isEmpty() || sname.isEmpty() || mname.isEmpty() || address.isEmpty() || ICST_ID.isEmpty() || password.isEmpty() || r_password.isEmpty();
            

            if(ICST_ID.isEmpty()){
                JOptionPane.showMessageDialog(null, "Generate ICTS ID first", "Error Submission", 0);
            }
            else if(noInput){
                JOptionPane.showMessageDialog(null, "Please fill up the form", "Error Submission", 0);
            }
            else if(mname.length() >= 2){
                JOptionPane.showMessageDialog(null, "One character only in Middle Initial", "Error Submission", 0);
            }

            else if(mname.charAt(0) >= '0' && mname.charAt(0) <= '9'){
                JOptionPane.showMessageDialog(null, "Middle Initial is a number", "Error Submission", 0);
            }

            else if(!r_password.equals(password)){
                JOptionPane.showMessageDialog(null, "Password and Retype Password are not the same", "Error Submission", 0);
            }
            else if(password.length() < 8){
                JOptionPane.showMessageDialog(null, "Password should be 8 characters long", "Error Submission", 0);
            }   
            else{
                JOptionPane.showMessageDialog(null, "Registration Success", "Success Submission", 1);
                
                //Reset Text, Enable Button Again, Reset Submit Button
                RegName[0].setText("");
                RegName[1].setText("");
                RegName[2].setText("");
                RegField[0].setText("");
                RegField[1].setText("");
                RegPW[0].setText("");
                RegPW[1].setText("");
                RegButton[0].setEnabled(true);
                RegButton[0].setVisible(true);
                RegButton[1].setBounds(260, 470, 230, 50);

                //Push to Array
                MemberID[memberCount] = ICST_ID;
                MemberPW[memberCount] = password;
                MemberFN[memberCount] = fname.toUpperCase();
                MemberSN[memberCount] = sname.toUpperCase();
                MemberMI[memberCount] = mname.toUpperCase();
                MemberAddress[memberCount] = address.toUpperCase();
                memberCount++;

                RegFrame.dispose();
                AdminFrame.setVisible(true);
            }
        }

        //Show Member
        if(Clicked.getSource() == AdminButton[1]){
            ShowFrame.setVisible(true);
        }

        if(Clicked.getSource() == ShowButton){
            boolean gotclick = false;
            String Areaformat = "";
            for(int i = 0; i < memberCount; i++){
                Areaformat = Areaformat + "[" + MemberSN[i] + ", " + MemberFN[i] + " " + MemberMI[i] + ".] [" + MemberID[i] + "] [" + MemberPW[i] + "] \n";
                Show.setText(Areaformat);
                gotclick = true;
            }
            if(gotclick){
                ShowButton.setText("Refresh");
            }
        }

        //Logout
        if(Clicked.getSource() == AdminButton[2]){
            Loginframe.setVisible(true);
            AdminFrame.dispose();
        }

    }
}
