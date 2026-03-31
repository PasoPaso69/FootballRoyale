/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.UI.Statistiche;

import com.mycompany.footballroyale.UI.Statistiche.StrategieView.VisualStrategy;
import com.mycompany.footballroyale.UI.Statistiche.VisualFactory;
import com.mycompany.footballroyale.domain.Enum.CriterioVisual;

/**
 *
 * @author 39327
 */
public class StatisticheFrame {
    
    public Boolean SelezionaMetodo(CriterioVisual criterio){
        
        VisualStrategy criterioScelto = VisualFactory.getStrategy(criterio);
        
    
    return true;}
}
