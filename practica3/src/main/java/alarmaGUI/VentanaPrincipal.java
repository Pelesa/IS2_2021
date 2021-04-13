package alarmaGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import estados.*;
import aplicacion.*;


public class VentanaPrincipal {

	private JFrame frame;

	private static Alarmas alarmas = new Alarmas(AlarmasState.AlarmasState()) ;


	private JList<Alarma> alarmasActivasList;//declaramos La Lista
	private DefaultListModel<Alarma> modeloActivas;//declaramos el Modelo
	private JScrollPane scrollListaActivas;

	private JList<Alarma> alarmasDesactivadasList;//declaramos La Lista
	private DefaultListModel<Alarma> modeloDesactivadas;//declaramos el Modelo
	private JScrollPane scrollListaDesactivadas;

	//Datos para nueva alarma:
	private JTextPane txtpIDalarma;
	private JSpinner timeSpinner;

	private Timer timerAlarma = new Timer();
	private SonidoAlarma timerTask = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 616, 418);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelCentral = new JPanel();
		frame.getContentPane().add(panelCentral);
		panelCentral.setLayout(null);

		JPanel panelLeft = new JPanel();
		panelLeft.setBounds(12, 5, 309, 365);
		panelCentral.add(panelLeft);
		panelLeft.setLayout(null);

		JPanel panelIdAlarma = new JPanel();
		panelIdAlarma.setBounds(0, 12, 297, 41);
		panelLeft.add(panelIdAlarma);

		JLabel lblIdAlarma = new JLabel("Id Alarma");
		lblIdAlarma.setVerticalAlignment(SwingConstants.BOTTOM);
		lblIdAlarma.setHorizontalAlignment(SwingConstants.CENTER);

		txtpIDalarma = new JTextPane();
		GroupLayout gl_panelIdAlarma = new GroupLayout(panelIdAlarma);
		gl_panelIdAlarma.setHorizontalGroup(
				gl_panelIdAlarma.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelIdAlarma.createSequentialGroup()
						.addComponent(lblIdAlarma, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtpIDalarma, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
						.addGap(12))
				);
		gl_panelIdAlarma.setVerticalGroup(
				gl_panelIdAlarma.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelIdAlarma.createSequentialGroup()
						.addGap(12)
						.addComponent(txtpIDalarma, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
						.addGap(12))
				.addGroup(gl_panelIdAlarma.createSequentialGroup()
						.addComponent(lblIdAlarma, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
						.addContainerGap())
				);
		panelIdAlarma.setLayout(gl_panelIdAlarma);

		JPanel panelHoraAlarma = new JPanel();
		panelHoraAlarma.setBounds(0, 85, 297, 41);
		panelLeft.add(panelHoraAlarma);
		panelHoraAlarma.setLayout(null);

		JLabel lblHoraAlarma = new JLabel("Hora Alarma");
		lblHoraAlarma.setVerticalAlignment(SwingConstants.BOTTOM);
		lblHoraAlarma.setBounds(12, 12, 102, 29);
		panelHoraAlarma.add(lblHoraAlarma);


		timeSpinner = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "dd-MM-yyyy HH:mm:ss");
		timeSpinner.setEditor(timeEditor);
		timeSpinner.setValue(new Date()); 
		timeSpinner.setBounds(149, 0, 148, 41);
		panelHoraAlarma.add(timeSpinner);

		JButton btnNuevaAlarma = new JButton("Nueva Alarma");
		btnNuevaAlarma.setBounds(49, 179, 210, 22);
		btnNuevaAlarma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Alarma a = new Alarma(txtpIDalarma.getText(), (Date)timeSpinner.getValue());
				alarmas.NuevaAlarma(a);
				actualizaAlarmas();
			}
		});
		panelLeft.add(btnNuevaAlarma);

		JButton btnApagar = new JButton("APAGAR");
		btnApagar.setBounds(49, 328, 210, 25);
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					timerTask.apaga();
				} catch (Exception e1) {}
			}
		});
		panelLeft.add(btnApagar);

		JPanel panelRight = new JPanel();
		panelRight.setBounds(329, 5, 287, 365);
		panelCentral.add(panelRight);
		panelRight.setLayout(null);

		JLabel lblAlarmasActivas = new JLabel("Alarmas Activas");
		alarmasActivasList = new JList<Alarma>();
		alarmasActivasList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		modeloActivas = new DefaultListModel<Alarma>();

		scrollListaActivas = new JScrollPane();
		scrollListaActivas.setViewportView(alarmasActivasList);

		JPanel panelActivas = new JPanel();
		panelActivas.setBounds(0, 0, 275, 149);
		panelActivas.add(lblAlarmasActivas);
		panelActivas.add(scrollListaActivas);
		panelRight.add(panelActivas);

		JPanel panelDesactivadas = new JPanel();
		panelDesactivadas.setBounds(0, 161, 275, 204);


		JLabel lblAlarmasDesactivadas = new JLabel("Alarmas Desactivadas");
		lblAlarmasDesactivadas.setBounds(59, 12, 156, 15);
		panelDesactivadas.add(lblAlarmasDesactivadas);

		alarmasDesactivadasList = new JList<Alarma>();
		alarmasDesactivadasList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		modeloDesactivadas = new DefaultListModel<Alarma>();

		scrollListaDesactivadas = new JScrollPane();
		scrollListaDesactivadas.setViewportView(alarmasDesactivadasList);

		panelDesactivadas.add(lblAlarmasDesactivadas);
		panelDesactivadas.add(scrollListaDesactivadas);
		panelRight.add(panelDesactivadas);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(80, 167, 117, 25);
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Alarma a = alarmasDesactivadasList.getSelectedValue();
					alarmas.BorraAlarma(a);
					a = alarmasActivasList.getSelectedValue();
					alarmas.BorraAlarma(a);
					actualizaAlarmas();
				} catch (Exception e1) {}
			}
		});
		panelDesactivadas.add(btnEliminar);

		JButton btnOff = new JButton("OFF");
		btnOff.setBounds(12, 130, 117, 25);
		btnOff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					Alarma a = alarmasActivasList.getSelectedValue();
					alarmas.AlarmaOff(a);
					actualizaAlarmas();
				} catch (Exception e1) {}
			}
		});
		panelDesactivadas.add(btnOff);

		JButton btnOn = new JButton("ON");
		btnOn.setBounds(141, 130, 117, 25);
		btnOn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Alarma a = alarmasDesactivadasList.getSelectedValue();
					alarmas.AlarmaOn(a);
					actualizaAlarmas();
				} catch (Exception e1) {}			
			}
		});
		panelDesactivadas.add(btnOn);
	}

	private void actualizaAlarmasActivas() {
		int i = 0;
		modeloActivas.clear();
		for(Alarma a : alarmas.alarmasActivadas()) {
			modeloActivas.add(i ,a);
			i++;
		}
		alarmasActivasList.setModel(modeloActivas);

	}

	private void actualizaAlarmasDesactivadas() {
		int i = 0;
		modeloDesactivadas.clear();
		for(Alarma a : alarmas.alarmasDesactivadas()) {
			modeloDesactivadas.add(i ,a);
			i++;
		}
		alarmasDesactivadasList.setModel(modeloDesactivadas);
	}

	/**
	 * Actualiza la visualizacion de las listas de alarmas, y asegura que la alarma programada sea la más cercana.
	 */
	private void actualizaAlarmas() {
		programaTimer();
		actualizaAlarmasActivas(); //ACtualiza la view de las listas activas
		actualizaAlarmasDesactivadas(); //ACtualiza la view de las listas desactivadas
	}

	/**
	 * Programa en el timer la alarma mas proxima.
	 */
	private void programaTimer() {
		try {
			if(alarmas.alarmaMasProxima() != null) {
				if (timerTask != null) timerTask.cancel();
				timerTask = new SonidoAlarma();
				timerAlarma.schedule(timerTask, alarmas.alarmaMasProxima().hora());
			}
		} catch (Exception e) {}
	}

	/**
	 * Clase de tipo TimerTask, ejecutada por el timer para hacer que la
	 * alarma suene.
	 */
	private class SonidoAlarma extends TimerTask {

		Timer t = new Timer();
		ApagaAlarma endTimer = new ApagaAlarma();
		public SonidoAlarma() {} 

		@Override
		public void run() {
			alarmas.sonando();
			t.schedule(endTimer, Alarmas.INTERVALO_SONAR);
		}

		public void apaga() {
			alarmas.Apagar();
			actualizaAlarmas();
		}

		/**
		 * Clase de tipo TimerTask, ejecutada por el timer de SonidoAlarma para asegurarse
		 * de que se apague transcurrido el tiempo indicado en alarmas.INTERVALO_SONAR.
		 */
		private class ApagaAlarma extends TimerTask {

			@Override
			public void run() {
				apaga();
			}	
		}
	}	
}