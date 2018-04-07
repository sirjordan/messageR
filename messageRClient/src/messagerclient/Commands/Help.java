/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagerclient.Commands;

import java.util.List;
import messageRCore.Contracts.CommandInterpetator;

/**
 *
 * @author maritn
 */
public class Help implements CommandInterpetator{

    @Override
    public void ExecuteCommand(List<String> args) {
        System.out.println("Helping here...");
    }
    
}
