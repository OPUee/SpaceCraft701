package lowbob.util.events;

import lowbob.LowBobPanel;

/**
 * Created by opuee on 05.10.17.
 */
public class PanelChangedEventArgs {

    private LowBobPanel m_next;

    public PanelChangedEventArgs(LowBobPanel panel) {
        this.m_next = panel;
    }

    public LowBobPanel getPanel() {
        return this.m_next;
    }
}
