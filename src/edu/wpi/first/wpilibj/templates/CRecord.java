///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
///*
// * This class will hopefully be responsible fore recording the robots 
// * movements and "replaying" them.
// */
//package edu.wpi.first.wpilibj.templates;
//import edu.wpi.first.wpilibj.*;
//
///**
// *
// * @author Fauzi
// */
//public class CRecord {
//    
//    Timer trRecord;
//    Timer trReplay;
//    LinkedListDouble dLsTimerDrive = new LinkedListDouble();
//    LinkedListDouble dLsTimerAiming = new LinkedListDouble();
//    LinkedListDouble dLsX = new LinkedListDouble();
//    LinkedListDouble dLsY = new LinkedListDouble();
//    LinkedListDouble dLsAiming = new LinkedListDouble();
//    boolean bAimUpBefore = false;
//    boolean bAimDownBefore = false;
//    boolean bRecord = false;
//    double iXBefore = 0;
//    double iYBefore = 0;
//    CButton btRecord = new CButton();
//    CButton btReplay = new CButton();
//    CButton btClear = new CButton();
//    CButton btPause = new CButton();
//   
//    public void run(Joystick joy, Cannon cannon, Drive driver)
//    {
//        btRecord.run(joy.getRawButton(Var.buttonRecord));
//        btReplay.run(joy.getRawButton(Var.buttonReplay));
//        btClear.run(joy.getRawButton(Var.buttonClearList));
//        btPause.run(joy.getRawButton(Var.buttonPauseRecord));
//        
//        if(btClear.gotPressed())
//                clearAll();
//        
//        if(btRecord.gotPressed())
//        {
//            if(btPause.gotPressed())
//            {
//                trRecord.stop();
//                bRecord = !bRecord;
//                
//                if(bRecord)
//                    trRecord.start();
//            }
//            
//            if(bRecord)
//            {
//                trRecord.start();
//                
//                if(joy.getX() != iXBefore && joy.getY() != iYBefore)
//                {
//                    iXBefore = joy.getX();
//                    iYBefore = joy.getY();
//                    dLsTimerDrive.add(trRecord.get());
//                    dLsX.add(joy.getX());
//                    dLsY.add(joy.getY());
//                }
//
//                if(cannon.btAimUp.gotPressed() == true || cannon.btAimDown.gotPressed() == true)
//                {
//                    if(cannon.btAimUp.gotPressed() != bAimUpBefore)
//                    {
//                        dLsTimerAiming.add(trRecord.get());
//                        bAimUpBefore = !bAimUpBefore;
//                        dLsAiming.add(Var.turretSpeed);
//                    }
//
//                    else if(cannon.btAimDown.gotPressed() != bAimDownBefore)
//                    {
//                        dLsTimerAiming.add(trRecord.get());
//                        bAimDownBefore = !bAimDownBefore;
//                        dLsAiming.add(-Var.turretSpeed);
//                    }
//                }
//
//                else
//                {
//                    dLsTimerAiming.add(trRecord.get());
//                    dLsAiming.add(0);
//                }
//            }
//        }
//            
//        else if(btReplay.gotPressed())
//        {
//            trRecord.stop();
//            double x, y;
//            int iDrivePos = 0;
//            int iAimPos = 0;
//         
//            trReplay.reset();
//            trReplay.start();
//            
//            while(trReplay.get() < trRecord.get())
//            {
//                if(btReplay.gotPressed())
//                    break;
//                
//                if(trReplay.get() == dLsTimerDrive.get(iDrivePos))
//                {
//                    iDrivePos++;
//                    y = dLsY.get(iDrivePos) * Math.abs(dLsY.get(iDrivePos));
//                    x = -dLsX.get(iDrivePos) * Math.abs(dLsX.get(iDrivePos)); 
//                    driver.setSpeed((-y+x), (y+x));
//                }
//                
//                if(trReplay.get() == dLsTimerAiming.get(iAimPos))
//                {
//                    iAimPos++;
//                    cannon.mShooter.set(dLsAiming.get(iAimPos));
//                }
//            }
//      
//            trReplay.stop();
//        }
//        
//        else if(!btRecord.gotPressed())
//            trRecord.stop();
//    }
//    
//    private void clearAll()
//    {
//        trRecord.stop();
//        trRecord.reset();
//        trReplay.stop();
//        trReplay.reset();
//        dLsTimerDrive.deleteAll();
//        dLsTimerAiming.deleteAll();
//        dLsX.deleteAll();
//        dLsY.deleteAll();
//        dLsAiming.deleteAll();
//        bAimUpBefore = false;
//        bAimDownBefore = false;
//        iXBefore = 0;
//        iYBefore = 0;
//    }
//}
