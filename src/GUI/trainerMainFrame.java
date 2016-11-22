/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import dataObjects.Nurse;
import dataObjects.Pokemon;
import data_access_objects.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Edward
 */
public class trainerMainFrame extends javax.swing.JFrame {
    int tid;
    /**
     * Creates new form pokeMainFrame
     */
    public trainerMainFrame(int tid) {
        initComponents();
        this.tid = tid;
        this.welcomeLabel.setText("Welcome to Trainer's Panel, Trainer "+ this.tid);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        welcomeLabel = new javax.swing.JLabel();
        displayPokemonButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        depositButton = new javax.swing.JButton();
        pickUpButton = new javax.swing.JButton();
        transferButton = new javax.swing.JButton();
        tradeButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        welcomeLabel.setText("Trainer's Panel, Welcome Trainer!");
        welcomeLabel.setToolTipText("");

        displayPokemonButton.setText("Show Your Pokemons");
        displayPokemonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayPokemonButtonActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        depositButton.setText("Deposit Selected Pokmeon");
        depositButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                depositButtonActionPerformed(evt);
            }
        });

        pickUpButton.setText("View and Pick up Deposited Pokemon");
        pickUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pickUpButtonActionPerformed(evt);
            }
        });

        transferButton.setText("Transfer Pokemon");
        transferButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transferButtonActionPerformed(evt);
            }
        });

        tradeButton.setText("Trade");
        tradeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tradeButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Deposit Egg");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Get Hatched Eggs");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(displayPokemonButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(depositButton, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pickUpButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(welcomeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(transferButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tradeButton))
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(displayPokemonButton)
                    .addComponent(depositButton)
                    .addComponent(pickUpButton)
                    .addComponent(transferButton)
                    .addComponent(tradeButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void displayPokemonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayPokemonButtonActionPerformed
        // TODO add your handling code here:
        TrainerController tc;
        tc = TrainerController.getInstance();
        List<Pokemon> pkm = tc.getPokemons(this.tid);
        HospitalNurseController hnc = HospitalNurseController.getInstance();
        List<Nurse> ns =hnc.getNurses();
            
        for(Nurse n: ns){
            int nid =n.getNurse_id();
            List<Pokemon> pm =hnc.getDepositPokemon(nid,tid);
            for(int i=0;i<pm.size();i++)
            {
                for(int j =0; j<pkm.size();j++){
                 if(pkm.get(j).getPid()==pm.get(i).getPid())
                    pkm.remove(pkm.get(j));
                }

            }
        }
       

        
        DefaultTableModel pkmModel = new DefaultTableModel(){
            @Override
            public String getColumnName(int index) {
                String[] columnNames = {"Pokemon Id", "Name", "CP","HP","Pokeball Type"};
                return columnNames[index];
            }
        };
        pkmModel.setColumnCount(5);
        for(int i =0;i<pkm.size();i++)
        {
            Object[] pkmData= {pkm.get(i).getPid(),pkm.get(i).getPname(), pkm.get(i).getCp(), pkm.get(i).getHP(),pkm.get(i).getPokeball()};
           pkmModel.addRow(pkmData);
        }

         this.jTable1.setModel(pkmModel);
         pkmModel.fireTableDataChanged();
         this.jTable1.setEnabled(true);

         setButtonEnabled(true);
    }//GEN-LAST:event_displayPokemonButtonActionPerformed

    private void setButtonEnabled(boolean a){
         this.depositButton.setEnabled(a);
         this.pickUpButton.setEnabled(a);
         this.tradeButton.setEnabled(a);
         this.transferButton.setEnabled(a);
    }
    private void depositButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_depositButtonActionPerformed
        // TODO add your handling code here:
       int selected_row = this.jTable1.getSelectedRow();
       Object selected_pid = this.jTable1.getModel().getValueAt(selected_row, 0);
       depositDialog dg = new depositDialog(this, true,(int) selected_pid, this.tid);
       dg.setVisible(true);

        setButtonEnabled(false);
    }//GEN-LAST:event_depositButtonActionPerformed

    private void pickUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pickUpButtonActionPerformed
        // TODO add your handling code here:
        returnPokemon rtD = new returnPokemon(this,true, this.tid);
        rtD.setVisible(true);
      setButtonEnabled(false);
    }//GEN-LAST:event_pickUpButtonActionPerformed

    private void transferButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transferButtonActionPerformed
        // TODO add your handling code here:
       int selected_row = this.jTable1.getSelectedRow();
       Object selected_pid = this.jTable1.getModel().getValueAt(selected_row, 0);
       if((int) selected_row>=0){
       TransferDialog tfD = new TransferDialog(this, true,(int) selected_pid, this.tid);
       tfD.setVisible(true);
        setButtonEnabled(false);
       }

    }//GEN-LAST:event_transferButtonActionPerformed

    private void tradeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tradeButtonActionPerformed
        // TODO add your handling code here:
       int selected_row = this.jTable1.getSelectedRow();
       Object selected_pid = this.jTable1.getModel().getValueAt(selected_row, 0);
       if((int) selected_row>=0){
       TradeDialog tfD = new TradeDialog(this, true,(int) selected_pid, this.tid, "Trainer");
       tfD.setVisible(true);
             setButtonEnabled(false);
     }
    }//GEN-LAST:event_tradeButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here
       DepositEggDialog deD = new DepositEggDialog(this, true,this.tid);
       deD.setVisible(true);
       setButtonEnabled(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        hatchedEggDialog hed = new hatchedEggDialog(this, true,this.tid);
        hed.setVisible(true);
        setButtonEnabled(false);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton depositButton;
    private javax.swing.JButton displayPokemonButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton pickUpButton;
    private javax.swing.JButton tradeButton;
    private javax.swing.JButton transferButton;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}

