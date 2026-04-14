/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.footballroyale.UI.Statistiche.StrategieView;

import com.mycompany.footballroyale.domain.Enum.CriterioClassifica;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 39327
 */
public interface VisualStrategy {
    
    
    void stampa(List<Map.Entry<?, Integer>> classificaOrdinata, CriterioClassifica titoloTarget);
}
