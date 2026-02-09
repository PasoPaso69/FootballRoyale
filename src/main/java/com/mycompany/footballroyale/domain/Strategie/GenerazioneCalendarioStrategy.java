/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.footballroyale.domain.Strategie;
import com.mycompany.footballroyale.domain.*;
import com.mycompany.footballroyale.domain.Enum.GiorniSettimanali;
import java.util.Date;

import java.util.List;
/**
 *
 * @author 39327
 */
public interface GenerazioneCalendarioStrategy {
    List<Partita> generaCalendario(List<Squadra> Squadre, List<Campetto> campetti,Date dataInizio,List<GiorniSettimanali> giorni,int PartitePerGiorno,List<Prenotazione> prenotazioni);
}
