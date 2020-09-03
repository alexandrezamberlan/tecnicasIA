package util; 

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import busca.AEstrela;
import busca.Aleatorio;
import busca.Antecessor;
import busca.Busca;
import busca.BuscaBidirecional;
import busca.BuscaIterativo;
import busca.BuscaLargura;
import busca.BuscaProfundidade;
import busca.Estado;
import busca.Heuristica;
import busca.MostraStatusConsole;
import busca.Nodo;
import busca.SubidaMontanha;
import exemplos.Estado8Puzzle;
import exemplos.EstadoJarros;
import exemplos.EstadoMapa;
import exemplos.EstadoRainhas;
import exemplos.HLAC;
import exemplos.MissionarioCanibal;
import exemplos.QuadradoMagico;
import exemplos.QuadradoMagicoB;


/**
 * Applet para demonstrar os algoritmos de Busca
 *
 * @author Jomi Fred Hubner
 */

public class AppletDemoBusca extends JApplet {
    boolean isStandalone = false;
    
    JTextArea text = new JTextArea();
    JComboBox cAlgoritmo;
    JComboBox cProblema;
    JButton para;
    JButton executa;
    JTextField visitados = new JTextField(30);
    JTextField tPars = new JTextField(10);
    JCheckBox comFechados;
    JCheckBox comPoda;
    
    Problema problema = null;
    Busca  algBusca = null;
    

    /** inicia a interface */
    public void init() {
        try {
            getContentPane().setLayout(new BorderLayout());
            executa = new JButton("Executa");
            executa.setEnabled(false);
            executa.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    executa();
                }
            });
            
            para = new JButton("Parar");
            para.setEnabled(false);
            para.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                	if (algBusca != null) {
                		algBusca.para();
                	}
                }
            });
            
            cAlgoritmo = new JComboBox();
            cProblema = new JComboBox();
            cProblema.addItem("<sem selecao>");
            cProblema.addItem(new Jarros());
            cProblema.addItem(new P_HLAC()); 
            cProblema.addItem(new P_MisCa()); 
            cProblema.addItem(new P_Mapa()); 
            cProblema.addItem(new P_Puzzle1()); 
            cProblema.addItem(new P_Puzzle2()); 
            cProblema.addItem(new P_Puzzle3()); 
            cProblema.addItem(new P_Rainhas()); 
            cProblema.addItem(new P_Quadrado1()); 
            cProblema.addItem(new P_Quadrado2());

            cProblema.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    executa.setEnabled(false);
                    tPars.setEditable(false);
                    tPars.setText("");
                    tPars.setToolTipText("sem parametros para este problema");
                    int sel = cProblema.getSelectedIndex();
                    if (sel > 0) {
                    	problema = (Problema)cProblema.getSelectedItem();
                    	if (problema.getToolTip() != null) {
                    	    tPars.setEditable(true);
                    	    tPars.setToolTipText(problema.getToolTip());
                    	}

                    	Estado inicial = problema.getInicial();
                    	Estado meta = problema.getMeta();
	                    text.setText(inicial.getDescricao());
	                    text.append("\nEstado inicial="+inicial);
	                    if (meta != null) {
	                        text.append("\nEstado meta="+EstadoMapa.nomes[EstadoMapa.getMeta()]);                        
	                    }
	                    executa.setEnabled(true);
	                    
	                    cAlgoritmo.removeAllItems();
	                    cAlgoritmo.addItem(new BuscaLargura());
	                    cAlgoritmo.addItem(new BuscaProfundidade());
	                    cAlgoritmo.addItem(new BuscaIterativo());
	                    try {
	                        Heuristica h = (Heuristica)inicial; // ve se implementa a interface Heuristica
	                        cAlgoritmo.addItem(new AEstrela());
	                    } catch (Exception e2) {}
	                    try {
	                        Aleatorio a = (Aleatorio)inicial; // ve se implementa a interface Aleatorio
	                        cAlgoritmo.addItem(new SubidaMontanha());
	                    } catch (Exception e2) {}
	                    try {
	                        Antecessor a = (Antecessor)inicial; // ve se implementa a interface Aleatorio
	                        cAlgoritmo.addItem(new BuscaBidirecional());
	                    } catch (Exception e2) {}
                    }
                }
            });
            
            JPanel panelN = new JPanel();
            panelN.setLayout(new GridLayout(0, 1));
            
            tPars.setEditable(false);
            tPars.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    try {
                    	problema.setParametros(tPars.getText());
                    } catch (Exception e) {
                        text.append("Erro: "+e);
                    }
                }
            });
            
            JPanel p = new JPanel();
            p.setLayout(new FlowLayout(FlowLayout.LEFT));
            p.add(new JLabel("Problema:"));
            p.add(cProblema);
            p.add(new JLabel("   Parametros:"));
            p.add(tPars);
            panelN.add(p);
            
            p = new JPanel();
            p.setLayout(new FlowLayout(FlowLayout.LEFT));
            p.add(new JLabel("Algoritmo:"));
            p.add(cAlgoritmo);
            p.add(executa);
            p.add(para);
            panelN.add(p);
            
            comFechados = new JCheckBox("usar 'fechados'", true);
            comFechados.setToolTipText("Se ativado, um nodo nao gera sucessor igual a um outro da lista de fechados (j� visitados), sen�o considera s� a ascendencia direta do nodo.");

            comPoda = new JCheckBox("podar", true);
            comPoda.setToolTipText("Se ativado, um nodo nao gera sucessor igual a um outro ja criado.");
            comPoda.addItemListener(new  ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.DESELECTED) {
                        comFechados.setSelected(false);
                    }
                }});

                
            p = new JPanel();
            p.setLayout(new FlowLayout(FlowLayout.LEFT));
            p.add(new JLabel("Nodos visitados:"));
            p.add(visitados);
            p.add(comPoda);
            p.add(comFechados);
            visitados.setEditable(false);
            
            getContentPane().add(panelN, BorderLayout.NORTH);
            getContentPane().add(new JScrollPane(text), BorderLayout.CENTER);
            getContentPane().add(p, BorderLayout.SOUTH);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    void executa() {
        para.setEnabled(true);
        executa.setEnabled(false);

        final Estado inicial = problema.getInicial();
    	final Estado meta = problema.getMeta();

        try {
        	algBusca = (Busca)cAlgoritmo.getSelectedItem();
        } catch (Exception e) {
        	System.err.println("Nao foi selecionado um algoritmo");
        	return;
        }
        
        visitados.setText("");
        text.setText(algBusca+"\n");
        text.append("Estado inicial="+inicial);
        if (meta != null) {
        	text.append("Estado meta="+meta);
        }

        
        algBusca.setPodar(comPoda.isSelected());
        algBusca.usarFechados(comFechados.isSelected());
        
        // tenta setar a profundidade (para o caso do algoritmo ser BP ou descendente)
        try {
        	BuscaProfundidade bp = (BuscaProfundidade)algBusca;
        	bp.setProfMax(problema.getProfundidade());
        } catch (Exception e) {}
        
        algBusca.setMostra(new MostraStatusConsole() {
            protected void mostraFim() {
                visitados.setText(getStatus().getVisitados()+" em "+getStatus().getTempoDecorrido()+" ms, profundidade="+getStatus().getProfundidade());        
            }
            protected void mostra() {
                mostraFim();
            }
            protected void println(String s) {
                text.append(s+"\n");
            }
        });            

        
        Thread busca = new Thread() {
            public void run() {
                try {
                    Nodo   n = null;
                    algBusca.setParar(false);
                    algBusca.novoStatus();
                    
                    try { 
                    	// testa se � o bi-direcional
                    	BuscaBidirecional bbd = (BuscaBidirecional)algBusca;
                    	n = bbd.busca(inicial, meta);
                    } catch (Exception e) {
                    	n = algBusca.busca(inicial); // � uma algoritmo "normal" do busca
                    }
                    
                    if (n != null) {
                        text.append("\n\nsolucao ("+n.getProfundidade()+" operacoes, custo="+n.g()+"):\n" + n.montaCaminho() + "\n\n");
                    } else {
                    	text.append("\n\nsem solucao!");
                    }
                } catch (Throwable e) {
                    text.append("Erro: "+e);
                } finally {
                    para.setEnabled(false);
                    executa.setEnabled(true);
                    text.setCaretPosition(text.getText().length());
                    algBusca.para();
                }                
            }
        };
        busca.start();
    }
    
    /** classes que mapeiam um problema para a interface do applet */
    
    abstract class Problema {
    	String id;
    	Problema(String s) { id = s; }
    	public String toString() {	return id; }
    	abstract Estado getInicial();
    	Estado getMeta() { return null; }
    	int getProfundidade() { return 20; }
    	String getToolTip() { return null; }
    	void setParametros(String p) { }
    }
    
    class Jarros extends Problema {
    	Jarros() { super("Jarros"); }
    	Estado getInicial() { return new EstadoJarros(0,0,"inicial"); }
    	Estado getMeta() { return new EstadoJarros(2,2,"meta"); }    	
    }

    class P_HLAC extends Problema {
    	P_HLAC() { super("Homem, Lobo, Carneiro e o Alface"); }
    	Estado getInicial() { return new HLAC('e', 'e', 'e', 'e', "inicial"); }
    	Estado getMeta() { return new HLAC('d', 'd', 'd', 'd', "meta"); }    	
    }

    class P_MisCa extends Problema {
    	P_MisCa() { super("Missionarios e Canibais"); }
    	Estado getInicial() { return new MissionarioCanibal(3,3,'e',"inicial"); }
    	Estado getMeta() { return new MissionarioCanibal(0,0,'d',"meta"); }    	
    }

    class P_Mapa extends Problema {
    	int inicial = 8;
    	P_Mapa() { super("Caminho em mapa"); EstadoMapa.setMeta(15); }
    	Estado getInicial() { return new EstadoMapa(inicial); }
    	Estado getMeta() { return new EstadoMapa(EstadoMapa.getMeta()); }
    	String getToolTip() { return "Informe a cidade inicial"; }
    	void setParametros(String p) { 
	        // procura o indice
	        for (int i=0; i<EstadoMapa.nomes.length; i++) {
	            if (EstadoMapa.nomes[i] == tPars.getText().charAt(0)) {
	                inicial = i;
	                break;
	            }
	        }
	        text.append("\nNovo estado inicial="+getInicial());
    	}
    }

    class P_Puzzle1 extends Problema {
    	P_Puzzle1() { super("8-Puzzle (estado inicial aleatorio)"); }
    	Estado getInicial() { return new Estado8Puzzle(); }
    	Estado getMeta() { return Estado8Puzzle.getEstadoMeta(); }    	
    	int getProfundidade() { return 30; }
    }
    class P_Puzzle2 extends Problema {
    	P_Puzzle2() { super("8-Puzzle (estado inicial facil)"); }
    	Estado getInicial() { return Estado8Puzzle.getEstadoFacil(); }
    	Estado getMeta() { return Estado8Puzzle.getEstadoMeta(); }    	
    }
    class P_Puzzle3 extends Problema {
    	P_Puzzle3() { super("8-Puzzle (estado inicial dificil)"); }
    	Estado getInicial() { return Estado8Puzzle.getEstadoDificil(); }
    	Estado getMeta() { return Estado8Puzzle.getEstadoMeta(); }    	
    	int getProfundidade() { return 80; }
    }
    
    class P_Rainhas extends Problema {
    	P_Rainhas() { super("8 Rainhas"); }
    	Estado getInicial() { return new EstadoRainhas(); }
    	String getToolTip() { return "Informe o numero de rainhas"; }
    	void setParametros(String p) { 
    		EstadoRainhas.setTamanho( Integer.parseInt(p));
    	}
    }
    
    class P_Quadrado1 extends Problema {
    	P_Quadrado1() { super("Quadrado magico (versao a)"); }
    	Estado getInicial() { return new QuadradoMagico(); }
    	String getToolTip() { return "Informe a dimensao do quadrado"; }
    	void setParametros(String p) { 
    		QuadradoMagico.setTamanho( Integer.parseInt(p));
    	}
    }
    class P_Quadrado2 extends Problema {
    	P_Quadrado2() { super("Quadrado magico (versao b)"); }
    	Estado getInicial() { return new QuadradoMagicoB(); }
    	String getToolTip() { return "Informe a dimensao do quadrado"; }
    	void setParametros(String p) { 
    		QuadradoMagico.setTamanho( Integer.parseInt(p));
    	}
    }

    
    public static void main(String[] args) {
        AppletDemoBusca applet = new AppletDemoBusca();
        applet.isStandalone = true;
        Frame frame;
        frame = new Frame() {
            protected void processWindowEvent(WindowEvent e) {
                super.processWindowEvent(e);
                if (e.getID() == WindowEvent.WINDOW_CLOSING) {
                    System.exit(0);
                }
            }
            public synchronized void setTitle(String title) {
                super.setTitle(title);
                enableEvents(AWTEvent.WINDOW_EVENT_MASK);
            }
        };
        frame.setTitle("Demonstracao de algoritmos de busca");
        frame.add(applet, BorderLayout.CENTER);
        applet.init();
        applet.start();
        frame.setSize(700,500);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
        frame.setVisible(true);
    }
}
