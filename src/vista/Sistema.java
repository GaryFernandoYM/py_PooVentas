package vista;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.DatosTienda;
import modelo.Detalle;
import modelo.Eventos;
import modelo.Productos;
import modelo.ProductosDAO;
import modelo.Proveedor;
import modelo.ProveedorDAO;
import modelo.Venta;
import modelo.VentaDAO;
import modelo.login;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import reportes.Excel;

/**
 *
 * @author Dash
 */
public class Sistema extends javax.swing.JFrame {

    Cliente cl = new Cliente();
    ClienteDAO client = new ClienteDAO();
    Proveedor pr = new Proveedor();
    ProveedorDAO prDAO = new ProveedorDAO();
    Productos pro = new Productos();
    ProductosDAO proDAO = new ProductosDAO();
    Venta v = new Venta();
    VentaDAO Vdao = new VentaDAO();
    Detalle Dv = new Detalle();
    DatosTienda datos = new DatosTienda();
    Eventos event = new Eventos();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel tmp = new DefaultTableModel();
    //KEY cantidad
    int item;
    //PARA TOTAL APAGAR
    double Totalpagar = 0.00;

    public Sistema() {
        initComponents();

        this.setLocationRelativeTo(null);
        //ocultando IDCliente
        jTextFieldIdCliente.setVisible(false);
        jTextFieldIDVentas.setVisible(false);
        jTextFieldIdPro.setVisible(false);
        jTextFieldIdProoveedor.setVisible(false);
        jTextFieldIdDatos.setVisible(false);
        //llamano la libreria swingx para autocompletarBOXPRODUCTO
        AutoCompleteDecorator.decorate(jComboBoxProveedorProducto);
        //llamando colsularProveedor
        proDAO.ConsultarProveedor(jComboBoxProveedorProducto);
        //ListarDatosTienda();
    }


    public void ListarCliente() {
        List<Cliente> ListarCl = client.ListarCliente();
        modelo = (DefaultTableModel) jTableCliente.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < ListarCl.size(); i++) {
            ob[0] = ListarCl.get(i).getId();
            ob[1] = ListarCl.get(i).getDni();
            ob[2] = ListarCl.get(i).getNombre();
            ob[3] = ListarCl.get(i).getTelefono();
            ob[4] = ListarCl.get(i).getDireccion();
            ob[5] = ListarCl.get(i).getOrigen();
            modelo.addRow(ob);

        }
        jTableCliente.setModel(modelo);
    }

    public void ListarProveedor() {
        List<Proveedor> ListarPR = prDAO.ListarProveedor();
        modelo = (DefaultTableModel) jTableProveedor.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < ListarPR.size(); i++) {
            ob[0] = ListarPR.get(i).getId();
            ob[1] = ListarPR.get(i).getRuc();
            ob[2] = ListarPR.get(i).getNombre();
            ob[3] = ListarPR.get(i).getTelefono();
            ob[4] = ListarPR.get(i).getCorreo();
            ob[5] = ListarPR.get(i).getDireccion();
            modelo.addRow(ob);
        }
        jTableProveedor.setModel(modelo);
    }

    public void LimpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;

        }
    }

    public void ListarProductos() {
        List<Productos> ListarPRO = proDAO.ListarProductos();
        modelo = (DefaultTableModel) jTableProducto.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < ListarPRO.size(); i++) {
            ob[0] = ListarPRO.get(i).getId();
            ob[1] = ListarPRO.get(i).getCodigo();
            ob[2] = ListarPRO.get(i).getNombre();
            ob[3] = ListarPRO.get(i).getProveedor();
            ob[4] = ListarPRO.get(i).getStock();
            ob[5] = ListarPRO.get(i).getPrecio();
            modelo.addRow(ob);
        }
        jTableProducto.setModel(modelo);
    }

    public void ListarDatosTienda() {
        datos = proDAO.BuscarDatos();
        jTextFieldIdDatos.setText("" + datos.getId());
        jTextFieldRucDatos.setText("" + datos.getRuc());
        jTextFieldNombreDatos.setText("" + datos.getNombre());
        jTextFieldTelefonoDatos.setText("" + datos.getTelefono());
        jTextFieldDireccionDatos.setText("" + datos.getDireccion());
        jTextFieldRazonDatos.setText("" + datos.getRazon());
    }

    public void ListarVentas() {
        List<Venta> ListarVenta = Vdao.ListarVentas();
        modelo = (DefaultTableModel) jTableVentas.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < ListarVenta.size(); i++) {
            ob[0] = ListarVenta.get(i).getId();
            ob[1] = ListarVenta.get(i).getCliente();
            ob[2] = ListarVenta.get(i).getVendedor();
            ob[3] = ListarVenta.get(i).getTotal();
            modelo.addRow(ob);
        }
        jTableVentas.setModel(modelo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButtonEliminarVenta = new javax.swing.JButton();
        jTextFieldCodigoVenta = new javax.swing.JTextField();
        jTextFieldDescripcionVenta = new javax.swing.JTextField();
        jTextFieldCantidadVenta = new javax.swing.JTextField();
        jTextFieldPrecioVenta = new javax.swing.JTextField();
        jTextFieldStoockDispnible = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableVenta = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabelTotal = new javax.swing.JLabel();
        jTextFieldRucVenta = new javax.swing.JTextField();
        jTextFieldNombreClienteVEnta = new javax.swing.JTextField();
        jButtonGenerarVenta = new javax.swing.JButton();
        jTextFieldTelefonoVenta = new javax.swing.JTextField();
        jTextFieldDireccionClienteVenta = new javax.swing.JTextField();
        jTextFieldOrigenCV = new javax.swing.JTextField();
        jTextFieldIdPro = new javax.swing.JTextField();
        jButtonPdfVentasRuc = new javax.swing.JButton();
        jButtonPdfVentasBoleta = new javax.swing.JButton();
        jButtonNuevoVenta = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldDniCliente = new javax.swing.JTextField();
        jTextFieldNombreCliente = new javax.swing.JTextField();
        jTextFieldTelefonoCliente = new javax.swing.JTextField();
        jTextFieldDireccionCliente = new javax.swing.JTextField();
        jTextFieldOrigenCliente = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableCliente = new javax.swing.JTable();
        jButtonGuardarCliente = new javax.swing.JButton();
        jButtonActualizarCliente = new javax.swing.JButton();
        jButtonEliminarCliente = new javax.swing.JButton();
        jButtonNuevoCliente = new javax.swing.JButton();
        jTextFieldIdCliente = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldRucProoveedor = new javax.swing.JTextField();
        jTextFieldNombreProveedor = new javax.swing.JTextField();
        jTextFieldTelefonoProveedor = new javax.swing.JTextField();
        jTextFieldDireccionProveedor = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableProveedor = new javax.swing.JTable();
        jButtonGuardarProveedor = new javax.swing.JButton();
        jButtonActualizarProveedor = new javax.swing.JButton();
        jButtonEliminarProveedor = new javax.swing.JButton();
        jButtonNuevoProveedor = new javax.swing.JButton();
        jTextFieldIdProoveedor = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldCorreoProveedor = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jTextFieldCodigoProducto = new javax.swing.JTextField();
        jTextFieldDescripcionProducto = new javax.swing.JTextField();
        jTextFieldStokProducto = new javax.swing.JTextField();
        jTextFieldPrecioProducto = new javax.swing.JTextField();
        jComboBoxProveedorProducto = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableProducto = new javax.swing.JTable();
        jButtonGuardarProducto = new javax.swing.JButton();
        jButtonEditarProducto = new javax.swing.JButton();
        jButtonEliminarProducto = new javax.swing.JButton();
        jButtonnuevoProducto = new javax.swing.JButton();
        jButtonExelProducto = new javax.swing.JButton();
        jTextFieldIDProducto = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jTextFieldIDVentas = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableVentas = new javax.swing.JTable();
        jButtonExelVentas = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jTextFieldRucDatos = new javax.swing.JTextField();
        jTextFieldNombreDatos = new javax.swing.JTextField();
        jTextFieldTelefonoDatos = new javax.swing.JTextField();
        jTextFieldDireccionDatos = new javax.swing.JTextField();
        jTextFieldRazonDatos = new javax.swing.JTextField();
        jButtonActualizarDatos = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jTextFieldIdDatos = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButtonNuevaVenta = new javax.swing.JButton();
        jButtonClientes = new javax.swing.JButton();
        jButtonUsuario = new javax.swing.JButton();
        jButtonProveedor = new javax.swing.JButton();
        jButtonProductos = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButtonDatosTienda = new javax.swing.JButton();
        jLabeVendedor = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jTabbedPane1.setEnabled(false);
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Codigo");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Descripcion");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Cantidad");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Precio");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Stock Disponible");

        jButtonEliminarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        jButtonEliminarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarVentaActionPerformed(evt);
            }
        });

        jTextFieldCodigoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCodigoVentaActionPerformed(evt);
            }
        });
        jTextFieldCodigoVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldCodigoVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCodigoVentaKeyTyped(evt);
            }
        });

        jTextFieldDescripcionVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldDescripcionVentaKeyTyped(evt);
            }
        });

        jTextFieldCantidadVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCantidadVentaActionPerformed(evt);
            }
        });
        jTextFieldCantidadVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldCantidadVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCantidadVentaKeyTyped(evt);
            }
        });

        jTextFieldPrecioVenta.setEditable(false);

        jTableVenta.setBorder(new javax.swing.border.MatteBorder(null));
        jTableVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "CANTIDAD", "PRECIO", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableVentaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableVenta);
        if (jTableVenta.getColumnModel().getColumnCount() > 0) {
            jTableVenta.getColumnModel().getColumn(0).setResizable(false);
            jTableVenta.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTableVenta.getColumnModel().getColumn(1).setResizable(false);
            jTableVenta.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTableVenta.getColumnModel().getColumn(2).setResizable(false);
            jTableVenta.getColumnModel().getColumn(2).setPreferredWidth(30);
            jTableVenta.getColumnModel().getColumn(3).setResizable(false);
            jTableVenta.getColumnModel().getColumn(3).setPreferredWidth(30);
            jTableVenta.getColumnModel().getColumn(4).setResizable(false);
            jTableVenta.getColumnModel().getColumn(4).setPreferredWidth(40);
        }

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("DNI");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("NOMBRE");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/PagoTatal.png"))); // NOI18N
        jLabel10.setText("TOTAL A PAGAR: S/");

        jLabelTotal.setText("-----------");
        jLabelTotal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelTotalMouseClicked(evt);
            }
        });

        jTextFieldRucVenta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldRucVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRucVentaActionPerformed(evt);
            }
        });
        jTextFieldRucVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldRucVentaKeyPressed(evt);
            }
        });

        jTextFieldNombreClienteVEnta.setEditable(false);

        jButtonGenerarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Nventas01.png"))); // NOI18N
        jButtonGenerarVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonGenerarVentaMouseClicked(evt);
            }
        });
        jButtonGenerarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerarVentaActionPerformed(evt);
            }
        });

        jButtonPdfVentasRuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pdf.png"))); // NOI18N
        jButtonPdfVentasRuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPdfVentasRucActionPerformed(evt);
            }
        });

        jButtonPdfVentasBoleta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pdf.png"))); // NOI18N
        jButtonPdfVentasBoleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPdfVentasBoletaActionPerformed(evt);
            }
        });

        jButtonNuevoVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo.png"))); // NOI18N
        jButtonNuevoVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonNuevoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoVentaActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Telefono");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setText("Direccion");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setText("Origen");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setText("Boleta");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("Factura");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(jLabel3))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jTextFieldCodigoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldDescripcionVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel9)
                                    .addComponent(jTextFieldNombreClienteVEnta, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jTextFieldRucVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addGap(112, 112, 112)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldIdPro, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTextFieldCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(74, 74, 74)
                                        .addComponent(jTextFieldPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(38, 38, 38)
                                                .addComponent(jLabel21)
                                                .addGap(49, 49, 49))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jTextFieldTelefonoVenta)
                                                .addGap(22, 22, 22)))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(85, 85, 85)
                                                .addComponent(jLabel33))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(52, 52, 52)
                                                .addComponent(jTextFieldDireccionClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(69, 69, 69)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jTextFieldOrigenCV, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jTextFieldStoockDispnible, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(101, 101, 101)
                                        .addComponent(jLabel34)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel8))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(337, 337, 337)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonPdfVentasRuc)
                                    .addComponent(jLabel36))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButtonPdfVentasBoleta)
                                        .addGap(38, 38, 38)
                                        .addComponent(jButtonGenerarVenta)))
                                .addGap(42, 42, 42)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonEliminarVenta)
                    .addComponent(jButtonNuevoVenta))
                .addGap(41, 41, 41))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(12, 12, 12)
                                .addComponent(jTextFieldCodigoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jTextFieldIdPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldDescripcionVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldStoockDispnible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel21)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldNombreClienteVEnta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldRucVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTelefonoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldDireccionClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldOrigenCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonEliminarVenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonNuevoVenta)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(jLabel35))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonPdfVentasRuc)
                            .addComponent(jButtonPdfVentasBoleta)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jLabelTotal))
                    .addComponent(jButtonGenerarVenta))
                .addContainerGap(91, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("1", jPanel2);

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Dni/Ruc:");

        jLabel13.setBackground(new java.awt.Color(0, 0, 0));
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Nombre:");

        jLabel14.setBackground(new java.awt.Color(0, 0, 0));
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Telefono:");

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Direccion:");

        jLabel16.setBackground(new java.awt.Color(0, 0, 0));
        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Origen:");

        jTextFieldDniCliente.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jTextFieldDniCliente.setText("EWEWDWDWDW");
        jTextFieldDniCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDniClienteActionPerformed(evt);
            }
        });

        jTextFieldNombreCliente.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jTextFieldNombreCliente.setText("WEWE");

        jTextFieldTelefonoCliente.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jTextFieldTelefonoCliente.setText("WEWEWE");
        jTextFieldTelefonoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTelefonoClienteActionPerformed(evt);
            }
        });

        jTextFieldDireccionCliente.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jTextFieldDireccionCliente.setText("WEWEWE");

        jTextFieldOrigenCliente.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jTextFieldOrigenCliente.setText("WEWEW");

        jTableCliente.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTableCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DNI/RUC", "NOMBRE", "TELEFONO", "DIRECCION", "ORIGEN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableCliente);
        if (jTableCliente.getColumnModel().getColumnCount() > 0) {
            jTableCliente.getColumnModel().getColumn(0).setResizable(false);
            jTableCliente.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTableCliente.getColumnModel().getColumn(1).setResizable(false);
            jTableCliente.getColumnModel().getColumn(1).setPreferredWidth(50);
            jTableCliente.getColumnModel().getColumn(2).setResizable(false);
            jTableCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTableCliente.getColumnModel().getColumn(3).setResizable(false);
            jTableCliente.getColumnModel().getColumn(3).setPreferredWidth(50);
            jTableCliente.getColumnModel().getColumn(4).setResizable(false);
            jTableCliente.getColumnModel().getColumn(4).setPreferredWidth(80);
            jTableCliente.getColumnModel().getColumn(5).setResizable(false);
            jTableCliente.getColumnModel().getColumn(5).setPreferredWidth(80);
        }

        jButtonGuardarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/GuardarTodo.png"))); // NOI18N
        jButtonGuardarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonGuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarClienteActionPerformed(evt);
            }
        });

        jButtonActualizarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/actualizar.png"))); // NOI18N
        jButtonActualizarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonActualizarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarClienteActionPerformed(evt);
            }
        });

        jButtonEliminarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        jButtonEliminarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarClienteActionPerformed(evt);
            }
        });

        jButtonNuevoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo.png"))); // NOI18N
        jButtonNuevoCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoClienteActionPerformed(evt);
            }
        });

        jTextFieldIdCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextFieldIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(715, 715, 715))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(33, 33, 33)
                                .addComponent(jTextFieldDniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addGap(33, 33, 33)
                                    .addComponent(jTextFieldNombreCliente))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel14)
                                        .addComponent(jLabel15)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(27, 27, 27)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextFieldOrigenCliente)
                                        .addComponent(jTextFieldDireccionCliente)
                                        .addComponent(jTextFieldTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jButtonGuardarCliente)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonNuevoCliente)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonActualizarCliente)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonEliminarCliente)))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonGuardarCliente, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonNuevoCliente, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonActualizarCliente, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonEliminarCliente, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldDniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTextFieldNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jTextFieldTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldDireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldOrigenCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))))
                .addGap(74, 74, 74))
        );

        jTabbedPane1.addTab("2", jPanel3);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("RUC:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("NOMBRE:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("TELEFONO:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("DIRECCION:");

        jTextFieldDireccionProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDireccionProveedorActionPerformed(evt);
            }
        });

        jTableProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "RUC", "NOMBRE", "TELEFONO", "CORREO", "DIRECCION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableProveedorMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableProveedor);
        if (jTableProveedor.getColumnModel().getColumnCount() > 0) {
            jTableProveedor.getColumnModel().getColumn(0).setResizable(false);
            jTableProveedor.getColumnModel().getColumn(0).setPreferredWidth(15);
            jTableProveedor.getColumnModel().getColumn(1).setResizable(false);
            jTableProveedor.getColumnModel().getColumn(1).setPreferredWidth(40);
            jTableProveedor.getColumnModel().getColumn(2).setResizable(false);
            jTableProveedor.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTableProveedor.getColumnModel().getColumn(3).setResizable(false);
            jTableProveedor.getColumnModel().getColumn(3).setPreferredWidth(50);
            jTableProveedor.getColumnModel().getColumn(4).setResizable(false);
            jTableProveedor.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTableProveedor.getColumnModel().getColumn(5).setResizable(false);
            jTableProveedor.getColumnModel().getColumn(5).setPreferredWidth(80);
        }

        jButtonGuardarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/GuardarTodo.png"))); // NOI18N
        jButtonGuardarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarProveedorActionPerformed(evt);
            }
        });

        jButtonActualizarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/actualizar.png"))); // NOI18N
        jButtonActualizarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarProveedorActionPerformed(evt);
            }
        });

        jButtonEliminarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        jButtonEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarProveedorActionPerformed(evt);
            }
        });

        jButtonNuevoProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo.png"))); // NOI18N
        jButtonNuevoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoProveedorActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("CORREO:");

        jTextFieldCorreoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCorreoProveedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel11))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextFieldNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldRucProoveedor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldTelefonoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldCorreoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldDireccionProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(jTextFieldIdProoveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButtonGuardarProveedor)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonNuevoProveedor)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonActualizarProveedor)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminarProveedor))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 792, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButtonNuevoProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonActualizarProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonEliminarProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonGuardarProveedor))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextFieldIdProoveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jTextFieldRucProoveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jTextFieldNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jTextFieldTelefonoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jTextFieldCorreoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jTextFieldDireccionProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(125, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("3", jPanel4);

        jPanel5.setForeground(new java.awt.Color(255, 255, 255));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("CODIGO:");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("DESCRPCION");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("CANTIDAD:");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("PRECIO:");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("PROVEEDOR:");

        jComboBoxProveedorProducto.setEditable(true);
        jComboBoxProveedorProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxProveedorProductoMouseClicked(evt);
            }
        });
        jComboBoxProveedorProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxProveedorProductoActionPerformed(evt);
            }
        });

        jTableProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CODIGO", "DESCRIPCION", "PROVEEDOR", "STOCK", "PRECIO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableProductoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTableProducto);
        if (jTableProducto.getColumnModel().getColumnCount() > 0) {
            jTableProducto.getColumnModel().getColumn(0).setResizable(false);
            jTableProducto.getColumnModel().getColumn(0).setPreferredWidth(15);
            jTableProducto.getColumnModel().getColumn(1).setResizable(false);
            jTableProducto.getColumnModel().getColumn(1).setPreferredWidth(50);
            jTableProducto.getColumnModel().getColumn(2).setResizable(false);
            jTableProducto.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTableProducto.getColumnModel().getColumn(3).setResizable(false);
            jTableProducto.getColumnModel().getColumn(3).setPreferredWidth(60);
            jTableProducto.getColumnModel().getColumn(4).setResizable(false);
            jTableProducto.getColumnModel().getColumn(4).setPreferredWidth(40);
            jTableProducto.getColumnModel().getColumn(5).setResizable(false);
            jTableProducto.getColumnModel().getColumn(5).setPreferredWidth(20);
        }

        jButtonGuardarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/GuardarTodo.png"))); // NOI18N
        jButtonGuardarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarProductoActionPerformed(evt);
            }
        });

        jButtonEditarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/actualizar.png"))); // NOI18N
        jButtonEditarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarProductoActionPerformed(evt);
            }
        });

        jButtonEliminarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        jButtonEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarProductoActionPerformed(evt);
            }
        });

        jButtonnuevoProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo.png"))); // NOI18N
        jButtonnuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonnuevoProductoActionPerformed(evt);
            }
        });

        jButtonExelProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        jButtonExelProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExelProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldStokProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldDescripcionProducto))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxProveedorProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jTextFieldIDProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(162, 162, 162)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButtonGuardarProducto)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonnuevoProducto)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEditarProducto)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonExelProducto)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminarProducto))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextFieldDescripcionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTextFieldStokProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jTextFieldPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jComboBoxProveedorProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
                .addComponent(jTextFieldIDProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButtonExelProducto)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonnuevoProducto)
                            .addComponent(jButtonGuardarProducto)
                            .addComponent(jButtonEditarProducto)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButtonEliminarProducto)
                        .addGap(2, 2, 2)))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("4", jPanel5);

        jTableVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CLIENTE", "VENDEDOR", "TOTAL"
            }
        ));
        jTableVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableVentasMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTableVentas);
        if (jTableVentas.getColumnModel().getColumnCount() > 0) {
            jTableVentas.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTableVentas.getColumnModel().getColumn(1).setPreferredWidth(60);
            jTableVentas.getColumnModel().getColumn(2).setPreferredWidth(60);
            jTableVentas.getColumnModel().getColumn(3).setPreferredWidth(60);
        }

        jButtonExelVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excel.png"))); // NOI18N
        jButtonExelVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExelVentasActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jTextField1.setText("Reporte de Ventas");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1019, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButtonExelVentas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldIDVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jButtonExelVentas))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextFieldIDVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("5", jPanel6);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setText("RUC");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel28.setText("NOMBRE");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setText("TELEFONO");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setText("DIRECCION");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel31.setText("RAZON SOCIA");

        jButtonActualizarDatos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonActualizarDatos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/actualizar.png"))); // NOI18N
        jButtonActualizarDatos.setText("ACTUALIZAR");
        jButtonActualizarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarDatosActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel32.setText("DATOS DE LA TIENDA");

        jTextFieldIdDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdDatosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel30)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(237, 237, 237)
                                    .addComponent(jTextFieldRucDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(263, 263, 263)
                                    .addComponent(jLabel27))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(164, 164, 164)
                                .addComponent(jTextFieldNombreDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(99, 99, 99))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addGap(141, 141, 141)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldTelefonoDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29)
                            .addComponent(jLabel31))
                        .addGap(213, 213, 213))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jTextFieldIdDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel32)
                        .addGap(395, 395, 395))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonActualizarDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(jTextFieldDireccionDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jTextFieldRazonDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(204, 204, 204))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jTextFieldIdDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel32)))
                .addGap(38, 38, 38)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(jLabel29)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRucDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTelefonoDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNombreDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31))
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDireccionDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldRazonDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonActualizarDatos)
                .addContainerGap(127, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("6", jPanel7);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 1070, 520));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 70, 550));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/encabezado.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 1040, 160));

        jButtonNuevaVenta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonNuevaVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Nventas.png"))); // NOI18N
        jButtonNuevaVenta.setText("Nueva Venta");
        jButtonNuevaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevaVentaActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonNuevaVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, -1, 50));

        jButtonClientes.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Clientes (2).png"))); // NOI18N
        jButtonClientes.setText("Clientes");
        jButtonClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClientesActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, 117, 50));

        jButtonUsuario.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 11)); // NOI18N
        jButtonUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Usuario (1).png"))); // NOI18N
        jButtonUsuario.setText("Usuario");
        jButtonUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 117, 50));

        jButtonProveedor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/proveedor.png"))); // NOI18N
        jButtonProveedor.setText("Proveedor");
        jButtonProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProveedorActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 200, -1, 50));

        jButtonProductos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/producto.png"))); // NOI18N
        jButtonProductos.setText("Productos");
        jButtonProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProductosActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 200, 130, 50));

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/campras.png"))); // NOI18N
        jButton5.setText("Ventas");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 200, 120, 50));

        jButtonDatosTienda.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonDatosTienda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/TiendaO (1).png"))); // NOI18N
        jButtonDatosTienda.setText("Informacion");
        jButtonDatosTienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDatosTiendaActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonDatosTienda, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 200, 140, 50));

        jLabeVendedor.setText("Mr.Robot");
        getContentPane().add(jLabeVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/robott.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 120, 130));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNuevaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevaVentaActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jButtonNuevaVentaActionPerformed

    private void jButtonEliminarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarVentaActionPerformed
        // TODO add your handling code here:
        modelo = (DefaultTableModel) jTableVenta.getModel();
        modelo.removeRow(jTableVenta.getSelectedRow());
        TotalPagar();
        jTextFieldCodigoVenta.requestFocus();
    }//GEN-LAST:event_jButtonEliminarVentaActionPerformed

    private void jTextFieldCodigoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCodigoVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCodigoVentaActionPerformed

    private void jTextFieldRucVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRucVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRucVentaActionPerformed

    private void jButtonClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClientesActionPerformed
        LimpiarTabla();
        ListarCliente();
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jButtonClientesActionPerformed

    private void jTextFieldTelefonoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTelefonoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTelefonoClienteActionPerformed

    private void jButtonGuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarClienteActionPerformed
        if (!"".equals(jTextFieldDniCliente.getText()) && !"".equals(jTextFieldNombreCliente.getText()) && !"".equals(jTextFieldTelefonoCliente.getText()) && !"".equals(jTextFieldDireccionCliente.getText())) {

            cl.setDni(Integer.parseInt(jTextFieldDniCliente.getText()));
            cl.setNombre(jTextFieldNombreCliente.getText());
            cl.setTelefono(Integer.parseInt(jTextFieldTelefonoCliente.getText()));
            cl.setDireccion(jTextFieldDireccionCliente.getText());
            cl.setOrigen(jTextFieldOrigenCliente.getText());
            client.RegistrarCliente(cl);
            LimpiarTabla();
            LimpiarCliente();
            ListarCliente();
            JOptionPane.showMessageDialog(null, "Cliente Registrado");

        } else {
            JOptionPane.showMessageDialog(null, "los campos estan vacios");
        }

    }//GEN-LAST:event_jButtonGuardarClienteActionPerformed

    private void jTableClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableClienteMouseClicked
        int fila = jTableCliente.rowAtPoint(evt.getPoint());
        jTextFieldIdCliente.setText(jTableCliente.getValueAt(fila, 0).toString());
        jTextFieldDniCliente.setText(jTableCliente.getValueAt(fila, 1).toString());
        jTextFieldNombreCliente.setText(jTableCliente.getValueAt(fila, 2).toString());
        jTextFieldTelefonoCliente.setText(jTableCliente.getValueAt(fila, 3).toString());
        jTextFieldDireccionCliente.setText(jTableCliente.getValueAt(fila, 4).toString());
        jTextFieldOrigenCliente.setText(jTableCliente.getValueAt(fila, 5).toString());

    }//GEN-LAST:event_jTableClienteMouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jButtonEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarClienteActionPerformed
        // TODO add your handling code here:
        if (!"".equals(jTextFieldIdCliente.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro deeliminar?");
            if (pregunta == 0) {
                int id = Integer.parseInt(jTextFieldIdCliente.getText());
                client.EliminarCliente(id);
                LimpiarTabla();
                LimpiarCliente();
                ListarCliente();
            }

        }
    }//GEN-LAST:event_jButtonEliminarClienteActionPerformed

    private void jTextFieldIdClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIdClienteActionPerformed

    private void jTextFieldDniClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDniClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDniClienteActionPerformed

    private void jButtonActualizarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarClienteActionPerformed
        //para verificar
        if ("".equals(jTextFieldIdCliente.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {

            if (!"".equals(jTextFieldDniCliente.getText()) && !"".equals(jTextFieldNombreCliente.getText()) && !"".equals(jTextFieldTelefonoCliente.getText()) && !"".equals(jTextFieldDireccionCliente.getText())) {
                cl.setDni(Integer.parseInt(jTextFieldDniCliente.getText()));
                cl.setNombre(jTextFieldNombreCliente.getText());
                cl.setTelefono(Integer.parseInt(jTextFieldTelefonoCliente.getText()));
                cl.setDireccion(jTextFieldDireccionCliente.getText());
                cl.setOrigen(jTextFieldOrigenCliente.getText());
                cl.setNombre(jTextFieldNombreCliente.getText());
                cl.setId(Integer.parseInt(jTextFieldIdCliente.getText()));
                client.ActualizarCliente(cl);
                JOptionPane.showMessageDialog(null, "Cliente Actualizado");
                LimpiarTabla();
                LimpiarCliente();
                ListarCliente();

            } else {
                JOptionPane.showMessageDialog(null, "Los campos estan vacios!!");
            }
        }

    }//GEN-LAST:event_jButtonActualizarClienteActionPerformed

    private void jButtonNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoClienteActionPerformed
        LimpiarCliente();
    }//GEN-LAST:event_jButtonNuevoClienteActionPerformed

    private void jButtonGuardarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarProveedorActionPerformed
        //para verificar si los campos estan vacios
        if (!"".equals(jTextFieldRucProoveedor.getText()) && !"".equals(jTextFieldNombreProveedor.getText()) && !"".equals(jTextFieldTelefonoProveedor.getText()) && !"".equals(jTextFieldCorreoProveedor.getText()) && !"".equals(jTextFieldDireccionProveedor.getText())) {
            pr.setRuc(Integer.parseInt(jTextFieldRucProoveedor.getText()));
            pr.setNombre(jTextFieldNombreProveedor.getText());
            pr.setTelefono(Integer.parseInt(jTextFieldTelefonoProveedor.getText()));
            pr.setCorreo(jTextFieldCorreoProveedor.getText());
            pr.setDireccion(jTextFieldDireccionProveedor.getText());
            prDAO.RegistrarProveedor(pr);
            LimpiarTabla();

            ListarProveedor();
            JOptionPane.showMessageDialog(null, "proveedor Registrado");

        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_jButtonGuardarProveedorActionPerformed

    private void jTextFieldDireccionProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDireccionProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDireccionProveedorActionPerformed

    private void jTextFieldCorreoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCorreoProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCorreoProveedorActionPerformed

    private void jButtonProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProveedorActionPerformed
        //abriendo la tabla Proveedor
        LimpiarTabla();
        ListarProveedor();

        jTabbedPane1.setSelectedIndex(2);

    }//GEN-LAST:event_jButtonProveedorActionPerformed

    private void jButtonGuardarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarProductoActionPerformed
        //para verificar si los campos estan vacios
        if (!"".equals(jTextFieldCodigoProducto.getText()) && !"".equals(jTextFieldDescripcionProducto.getText()) && !"".equals(jComboBoxProveedorProducto.getSelectedItem()) && !"".equals(jTextFieldStokProducto.getText()) && !"".equals(jTextFieldPrecioProducto.getText())) {
            pro.setCodigo(jTextFieldCodigoProducto.getText());
            pro.setNombre(jTextFieldDescripcionProducto.getText());
            pro.setProveedor(jComboBoxProveedorProducto.getSelectedItem().toString());
            pro.setStock(Integer.parseInt(jTextFieldStokProducto.getText()));
            pro.setPrecio(Double.parseDouble(jTextFieldPrecioProducto.getText()));
            proDAO.RegistrarProductos(pro);
            LimpiarTabla();

            ListarProductos();
            JOptionPane.showMessageDialog(null, "Producto Registrado");

        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_jButtonGuardarProductoActionPerformed

    private void jTableProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProveedorMouseClicked
        int fila = jTableProveedor.rowAtPoint(evt.getPoint());
        jTextFieldIdProoveedor.setText(jTableProveedor.getValueAt(fila, 0).toString());
        jTextFieldRucProoveedor.setText(jTableProveedor.getValueAt(fila, 1).toString());
        jTextFieldNombreProveedor.setText(jTableProveedor.getValueAt(fila, 2).toString());
        jTextFieldTelefonoProveedor.setText(jTableProveedor.getValueAt(fila, 3).toString());
        jTextFieldCorreoProveedor.setText(jTableProveedor.getValueAt(fila, 4).toString());
        jTextFieldDireccionProveedor.setText(jTableProveedor.getValueAt(fila, 5).toString());

    }//GEN-LAST:event_jTableProveedorMouseClicked

    private void jButtonEliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarProveedorActionPerformed
        if (!"".equals(jTextFieldIdProoveedor.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro deeliminar?");
            if (pregunta == 0) {
                int id = Integer.parseInt(jTextFieldIdProoveedor.getText());
                prDAO.EliminarProveedor(id);
                LimpiarTabla();
                ListarProveedor();
                LimpiarProovedor();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");

        }
    }//GEN-LAST:event_jButtonEliminarProveedorActionPerformed

    private void jButtonActualizarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarProveedorActionPerformed
        //para verificar si la fila esta seleccionada
        if ("".equals(jTextFieldIdProoveedor.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
            //si esque no esta vacio
        } else {

            if (!"".equals(jTextFieldRucProoveedor.getText()) && !"".equals(jTextFieldNombreProveedor.getText()) && !"".equals(jTextFieldTelefonoProveedor.getText()) && !"".equals(jTextFieldCorreoProveedor.getText()) && !"".equals(jTextFieldDireccionProveedor.getText())) {
                pr.setRuc(Integer.parseInt(jTextFieldRucProoveedor.getText()));
                pr.setNombre(jTextFieldNombreProveedor.getText());
                pr.setTelefono(Integer.parseInt(jTextFieldTelefonoProveedor.getText()));
                pr.setCorreo(jTextFieldCorreoProveedor.getText());
                pr.setDireccion(jTextFieldDireccionProveedor.getText());
                pr.setId(Integer.parseInt(jTextFieldIdProoveedor.getText()));
                prDAO.ActualizarProveedor(pr);
                JOptionPane.showMessageDialog(null, "proveedor modificado");
                LimpiarTabla();
                ListarProveedor();
                LimpiarProovedor();

            } else {
                JOptionPane.showMessageDialog(null, "Los campos estan vacios!!");
            }
        }
    }//GEN-LAST:event_jButtonActualizarProveedorActionPerformed

    private void jButtonNuevoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoProveedorActionPerformed
        // TODO add your handling code here:
        LimpiarProovedor();
    }//GEN-LAST:event_jButtonNuevoProveedorActionPerformed

    private void jButtonProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProductosActionPerformed
        //abriendo la tabla Proveedor
        LimpiarTabla();
        ListarProductos();

        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_jButtonProductosActionPerformed

    private void jTableProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProductoMouseClicked
        // para seleccionar una fila de la tabla producto
        int fila = jTableProducto.rowAtPoint(evt.getPoint());
        jTextFieldIDProducto.setText(jTableProducto.getValueAt(fila, 0).toString());
        jTextFieldCodigoProducto.setText(jTableProducto.getValueAt(fila, 1).toString());
        jTextFieldDescripcionProducto.setText(jTableProducto.getValueAt(fila, 2).toString());
        jComboBoxProveedorProducto.setSelectedItem(jTableProducto.getValueAt(fila, 3).toString());
        jTextFieldStokProducto.setText(jTableProducto.getValueAt(fila, 4).toString());
        jTextFieldPrecioProducto.setText(jTableProducto.getValueAt(fila, 5).toString());
    }//GEN-LAST:event_jTableProductoMouseClicked

    private void jButtonEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarProductoActionPerformed
        // TODO add your handling code here:
        if (!"".equals(jTextFieldIDProducto.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar?");
            if (pregunta == 0) {
                int id = Integer.parseInt(jTextFieldIDProducto.getText());
                proDAO.EliminarProductos(id);
                LimpiarTabla();
                ListarProductos();
                LimpiarProDucto();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");

        }
    }//GEN-LAST:event_jButtonEliminarProductoActionPerformed

    private void jButtonEditarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarProductoActionPerformed
        //para verificar si la fila esta seleccionada
        if ("".equals(jTextFieldIDProducto.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
            //si esque no esta vacio
        } else {

            if (!"".equals(jTextFieldCodigoProducto.getText()) && !"".equals(jTextFieldDescripcionProducto.getText()) && !"".equals(jComboBoxProveedorProducto.getSelectedItem()) && !"".equals(jTextFieldStokProducto.getText()) && !"".equals(jTextFieldPrecioProducto.getText())) {
                pro.setCodigo(jTextFieldCodigoProducto.getText());
                pro.setNombre(jTextFieldDescripcionProducto.getText());
                pro.setProveedor(jComboBoxProveedorProducto.getSelectedItem().toString());
                pro.setStock(Integer.parseInt(jTextFieldStokProducto.getText()));
                pro.setPrecio(Double.parseDouble(jTextFieldPrecioProducto.getText()));
                pro.setId(Integer.parseInt(jTextFieldIDProducto.getText()));
                proDAO.ActualizarProducto(pro);
                JOptionPane.showMessageDialog(null, "Producto Actualizado");
                LimpiarTabla();
                ListarProductos();
                LimpiarProDucto();

            } else {
                JOptionPane.showMessageDialog(null, "Los campos estan vacios!!");
            }
        }
    }//GEN-LAST:event_jButtonEditarProductoActionPerformed

    private void jButtonnuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonnuevoProductoActionPerformed
        // TODO add your handling code here:
        LimpiarProDucto();
    }//GEN-LAST:event_jButtonnuevoProductoActionPerformed

    private void jButtonExelProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExelProductoActionPerformed
        //llamando exel
        Excel.reporte();
    }//GEN-LAST:event_jButtonExelProductoActionPerformed

    private void jTextFieldCodigoVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCodigoVentaKeyPressed
        //Condicionando la tecla ENTER
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //verificando que la celda no este vacia
            if (!"".equals(jTextFieldCodigoVenta.getText())) {
                String cod = jTextFieldCodigoVenta.getText();
                pro = proDAO.BuscarPRO(cod);
                //verificando Si el Producto existe
                if (pro.getNombre() != null) {
                    jTextFieldDescripcionVenta.setText("" + pro.getNombre());
                    jTextFieldPrecioVenta.setText("" + pro.getPrecio());
                    jTextFieldStoockDispnible.setText("" + pro.getStock());
                    jTextFieldCantidadVenta.requestFocus();
                } else {
                    LimpiarVenta();
                    jTextFieldCodigoVenta.requestFocus();

                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el codigo del producto");
                jTextFieldCodigoVenta.requestFocus();

            }

        }
    }//GEN-LAST:event_jTextFieldCodigoVentaKeyPressed

    private void jTextFieldCantidadVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCantidadVentaKeyPressed
        // Validanto que la cantidad no supere al stock
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(jTextFieldCantidadVenta.getText())) {
                String cod = jTextFieldCodigoVenta.getText();
                String descripcion = jTextFieldDescripcionVenta.getText();
                int cantidad = Integer.parseInt(jTextFieldCantidadVenta.getText());
                double precio = Double.parseDouble(jTextFieldPrecioVenta.getText());
                double total = cantidad * precio;
                int stock = Integer.parseInt(jTextFieldStoockDispnible.getText());

                if (stock >= cantidad) {
                    item = item + 1;
                    tmp = (DefaultTableModel) jTableVenta.getModel();
                    //para que el Stock no sea mayor
                    for (int i = 0; i < jTableVenta.getRowCount(); i++) {
                        if (jTableVenta.getValueAt(i, 1).equals(jTextFieldDescripcionVenta.getText())) {
                            JOptionPane.showMessageDialog(null, "El Producto ya es registrado");
                            return;
                        }

                    }
                    ArrayList lista = new ArrayList();
                    lista.add(item);
                    lista.add(cod);
                    lista.add(descripcion);
                    lista.add(cantidad);
                    lista.add(precio);
                    lista.add(total);
                    Object[] O = new Object[5];
                    O[0] = lista.get(1);
                    O[1] = lista.get(2);
                    O[2] = lista.get(3);
                    O[3] = lista.get(4);
                    O[4] = lista.get(5);
                    tmp.addRow(O);
                    jTableVenta.setModel(tmp);
                    TotalPagar();
                    LimpiarVenta();
                    jTextFieldCodigoVenta.requestFocus();

                } else {
                    JOptionPane.showMessageDialog(null, "Stock no disponible");

                }

            } else {
                JOptionPane.showMessageDialog(null, "Ingrese Cantida");

            }

        }

    }//GEN-LAST:event_jTextFieldCantidadVentaKeyPressed

    private void jTextFieldRucVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldRucVentaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(jTextFieldRucVenta.getText())) {
                int dni = Integer.parseInt(jTextFieldRucVenta.getText());
                cl = client.BuscarCliente(dni);
                //Verificando si hay resultado en el cliente
                if (cl.getNombre() != null) {
                    jTextFieldNombreClienteVEnta.setText(" " + cl.getNombre());
                    jTextFieldTelefonoVenta.setText(" " + cl.getTelefono());
                    jTextFieldDireccionClienteVenta.setText("" + cl.getDireccion());
                    jTextFieldOrigenCV.setText(" " + cl.getOrigen());
                } else {
                    jTextFieldRucVenta.setText(" ");
                    JOptionPane.showMessageDialog(null, "El cliente no Existe");
                }

            }
        }
    }//GEN-LAST:event_jTextFieldRucVentaKeyPressed

    private void jButtonGenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerarVentaActionPerformed
        // TODO add your handling code here:
        if (jTableVenta.getRowCount() > 0) {
            if (!"".equals(jTextFieldNombreClienteVEnta.getText())) {
                RegistrarVenta();
                RegistrarDetalle();
                ActualizarStock();

                JOptionPane.showMessageDialog(this, "Venta Registrada");
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese un Cliente");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Noy productos en la venta");
        }

    }//GEN-LAST:event_jButtonGenerarVentaActionPerformed

    private void jComboBoxProveedorProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxProveedorProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxProveedorProductoActionPerformed

    private void jComboBoxProveedorProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxProveedorProductoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxProveedorProductoMouseClicked

    private void jTextFieldCantidadVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCantidadVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCantidadVentaActionPerformed

    private void jTextFieldIdDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdDatosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIdDatosActionPerformed

    private void jTableVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableVentaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableVentaMouseClicked

    private void jLabelTotalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelTotalMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelTotalMouseClicked

    private void jButtonGenerarVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonGenerarVentaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonGenerarVentaMouseClicked

    private void jTextFieldCodigoVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCodigoVentaKeyTyped
        // TODO add your handling code here:
        event.PressNumero(evt);
    }//GEN-LAST:event_jTextFieldCodigoVentaKeyTyped

    private void jTextFieldDescripcionVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDescripcionVentaKeyTyped
        // TODO add your handling code here:
        event.PressLetras(evt);
    }//GEN-LAST:event_jTextFieldDescripcionVentaKeyTyped

    private void jTextFieldCantidadVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCantidadVentaKeyTyped
        // TODO add your handling code here:
        event.PressNumero(evt);
    }//GEN-LAST:event_jTextFieldCantidadVentaKeyTyped

    private void jButtonActualizarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarDatosActionPerformed
        // TODO add your handling code here:
        if (!"".equals(jTextFieldRucDatos.getText()) && !"".equals(jTextFieldNombreDatos.getText()) && !"".equals(jTextFieldTelefonoDatos.getText()) && !"".equals(jTextFieldDireccionDatos.getText()) && !"".equals(jTextFieldRazonDatos.getText())) {
            datos.setRuc(Integer.parseInt(jTextFieldRucDatos.getText()));
            datos.setNombre(jTextFieldNombreDatos.getText());
            datos.setTelefono(Integer.parseInt(jTextFieldTelefonoDatos.getText()));
            datos.setDireccion(jTextFieldDireccionDatos.getText());
            datos.setRazon(jTextFieldRazonDatos.getText());
            datos.setId(Integer.parseInt(jTextFieldIdDatos.getText()));
            proDAO.ActualizarDatos(datos);
            JOptionPane.showMessageDialog(null, "Datos de la tienda Actualizados");
            LimpiarTabla();
            ListarDatosTienda();

        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios!!");
        }

    }//GEN-LAST:event_jButtonActualizarDatosActionPerformed

    private void jButtonDatosTiendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDatosTiendaActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(5);
    }//GEN-LAST:event_jButtonDatosTiendaActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(4);
        LimpiarTabla();
        ListarVentas();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTableVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableVentasMouseClicked
        // TODO add your handling code here:
        int fila = jTableVentas.rowAtPoint(evt.getPoint());
        jTextFieldIDVentas.setText(jTableVentas.getValueAt(fila, 0).toString());
    }//GEN-LAST:event_jTableVentasMouseClicked

    private void jButtonUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUsuarioActionPerformed
 
    }//GEN-LAST:event_jButtonUsuarioActionPerformed

    private void jButtonPdfVentasRucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPdfVentasRucActionPerformed
        // TODO add your handling code here:

        if (jTableVenta.getRowCount() > 0) {
            if (!"".equals(jTextFieldNombreClienteVEnta.getText())) {
                Factura();

                JOptionPane.showMessageDialog(this, "Factura Registrada");
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese un Cliente");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Noy productos en la venta");
        }
    }//GEN-LAST:event_jButtonPdfVentasRucActionPerformed

    private void jButtonPdfVentasBoletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPdfVentasBoletaActionPerformed
        // TODO add your handling code here:
        if (jTableVenta.getRowCount() > 0) {
            if (!"".equals(jTextFieldNombreClienteVEnta.getText())) {
                Boleta();

                JOptionPane.showMessageDialog(this, "Boleta Registrada");
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese un Cliente");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Noy productos en la venta");
        }
    }//GEN-LAST:event_jButtonPdfVentasBoletaActionPerformed

    private void jButtonNuevoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoVentaActionPerformed
        // TODO add your handling code here:
        LimpiarTablaVenta();
        LimpiarClienteVenta();
    }//GEN-LAST:event_jButtonNuevoVentaActionPerformed

    private void jButtonExelVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExelVentasActionPerformed
        // TODO add your handling code here:
        Excel.reporteVentas();
    }//GEN-LAST:event_jButtonExelVentasActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sistema().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButtonActualizarCliente;
    private javax.swing.JButton jButtonActualizarDatos;
    private javax.swing.JButton jButtonActualizarProveedor;
    private javax.swing.JButton jButtonClientes;
    private javax.swing.JButton jButtonDatosTienda;
    private javax.swing.JButton jButtonEditarProducto;
    private javax.swing.JButton jButtonEliminarCliente;
    private javax.swing.JButton jButtonEliminarProducto;
    private javax.swing.JButton jButtonEliminarProveedor;
    private javax.swing.JButton jButtonEliminarVenta;
    private javax.swing.JButton jButtonExelProducto;
    private javax.swing.JButton jButtonExelVentas;
    private javax.swing.JButton jButtonGenerarVenta;
    private javax.swing.JButton jButtonGuardarCliente;
    private javax.swing.JButton jButtonGuardarProducto;
    private javax.swing.JButton jButtonGuardarProveedor;
    private javax.swing.JButton jButtonNuevaVenta;
    private javax.swing.JButton jButtonNuevoCliente;
    private javax.swing.JButton jButtonNuevoProveedor;
    private javax.swing.JButton jButtonNuevoVenta;
    private javax.swing.JButton jButtonPdfVentasBoleta;
    private javax.swing.JButton jButtonPdfVentasRuc;
    private javax.swing.JButton jButtonProductos;
    private javax.swing.JButton jButtonProveedor;
    private javax.swing.JButton jButtonUsuario;
    private javax.swing.JButton jButtonnuevoProducto;
    private javax.swing.JComboBox<String> jComboBoxProveedorProducto;
    private javax.swing.JLabel jLabeVendedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelTotal;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableCliente;
    private javax.swing.JTable jTableProducto;
    private javax.swing.JTable jTableProveedor;
    private javax.swing.JTable jTableVenta;
    private javax.swing.JTable jTableVentas;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldCantidadVenta;
    private javax.swing.JTextField jTextFieldCodigoProducto;
    private javax.swing.JTextField jTextFieldCodigoVenta;
    private javax.swing.JTextField jTextFieldCorreoProveedor;
    private javax.swing.JTextField jTextFieldDescripcionProducto;
    private javax.swing.JTextField jTextFieldDescripcionVenta;
    private javax.swing.JTextField jTextFieldDireccionCliente;
    private javax.swing.JTextField jTextFieldDireccionClienteVenta;
    private javax.swing.JTextField jTextFieldDireccionDatos;
    private javax.swing.JTextField jTextFieldDireccionProveedor;
    private javax.swing.JTextField jTextFieldDniCliente;
    private javax.swing.JTextField jTextFieldIDProducto;
    private javax.swing.JTextField jTextFieldIDVentas;
    private javax.swing.JTextField jTextFieldIdCliente;
    private javax.swing.JTextField jTextFieldIdDatos;
    private javax.swing.JTextField jTextFieldIdPro;
    private javax.swing.JTextField jTextFieldIdProoveedor;
    private javax.swing.JTextField jTextFieldNombreCliente;
    private javax.swing.JTextField jTextFieldNombreClienteVEnta;
    private javax.swing.JTextField jTextFieldNombreDatos;
    private javax.swing.JTextField jTextFieldNombreProveedor;
    private javax.swing.JTextField jTextFieldOrigenCV;
    private javax.swing.JTextField jTextFieldOrigenCliente;
    private javax.swing.JTextField jTextFieldPrecioProducto;
    private javax.swing.JTextField jTextFieldPrecioVenta;
    private javax.swing.JTextField jTextFieldRazonDatos;
    private javax.swing.JTextField jTextFieldRucDatos;
    private javax.swing.JTextField jTextFieldRucProoveedor;
    private javax.swing.JTextField jTextFieldRucVenta;
    private javax.swing.JTextField jTextFieldStokProducto;
    private javax.swing.JTextField jTextFieldStoockDispnible;
    private javax.swing.JTextField jTextFieldTelefonoCliente;
    private javax.swing.JTextField jTextFieldTelefonoDatos;
    private javax.swing.JTextField jTextFieldTelefonoProveedor;
    private javax.swing.JTextField jTextFieldTelefonoVenta;
    // End of variables declaration//GEN-END:variables
    private void LimpiarCliente() {
        jTextFieldIdCliente.setText("");
        jTextFieldDniCliente.setText("");
        jTextFieldNombreCliente.setText("");
        jTextFieldTelefonoCliente.setText("");
        jTextFieldDireccionCliente.setText("");
        jTextFieldOrigenCliente.setText("");
    }

    private void LimpiarProovedor() {
        jTextFieldIdProoveedor.setText("");
        jTextFieldRucProoveedor.setText("");
        jTextFieldNombreProveedor.setText("");
        jTextFieldTelefonoProveedor.setText("");
        jTextFieldCorreoProveedor.setText("");
        jTextFieldDireccionProveedor.setText("");
    }

    private void LimpiarProDucto() {
        jTextFieldIDProducto.setText("");
        jTextFieldCodigoProducto.setText("");
        jTextFieldDescripcionProducto.setText("");
        jComboBoxProveedorProducto.setSelectedItem("");
        jTextFieldStokProducto.setText("");
        jTextFieldPrecioProducto.setText("");
    }

    private void TotalPagar() {
        Totalpagar = 0.00;
        int numFila = jTableVenta.getRowCount();
        for (int i = 0; i < numFila; i++) {
            double cal = Double.parseDouble(String.valueOf(jTableVenta.getModel().getValueAt(i, 4)));
            Totalpagar = Totalpagar + cal;
        }
        jLabelTotal.setText(String.format("%.2f", Totalpagar));
    }

    private void LimpiarVenta() {
        jTextFieldCodigoVenta.setText("");
        jTextFieldDescripcionVenta.setText("");
        jTextFieldCantidadVenta.setText("");
        jTextFieldStoockDispnible.setText("");
        jTextFieldPrecioVenta.setText("");
        jTextFieldIDVentas.setText("");

    }

    private void RegistrarVenta() {
        String cliente = jTextFieldNombreClienteVEnta.getText();
        String vendedor = jLabeVendedor.getText();
        double monto = Totalpagar;
        v.setCliente(cliente);
        v.setVendedor(vendedor);
        v.setTotal(monto);
        Vdao.RegistrarVenta(v);
    }

    private void RegistrarDetalle() {
        int id = Vdao.IdVenta();

        for (int i = 0; i < jTableVenta.getRowCount(); i++) {
            String cod = jTableVenta.getValueAt(i, 0).toString();
            int cant = Integer.parseInt(jTableVenta.getValueAt(i, 2).toString());
            double precio = Double.parseDouble(jTableVenta.getValueAt(i, 3).toString());

            Dv.setCod_pro(cod);
            Dv.setCantidad(cant);
            Dv.setPrecio(precio);
            Dv.setId(id);
            Vdao.RegistrarDetalle(Dv);

        }

    }

    private void ActualizarStock() {
        for (int i = 0; i < jTableVenta.getRowCount(); i++) {
            String cod = jTableVenta.getValueAt(i, 0).toString();
            int cant = Integer.parseInt(jTableVenta.getValueAt(i, 2).toString());
            pro = proDAO.BuscarPRO(cod);
            int StockActual = pro.getStock() - cant;
            Vdao.ActualizarStoock(StockActual, cod);

        }
    }

    private void LimpiarTablaVenta() {
        tmp = (DefaultTableModel) jTableVenta.getModel();
        int fila = jTableVenta.getRowCount();
        for (int i = 0; i < fila; i++) {
            tmp.removeRow(0);

        }
    }

    private void LimpiarClienteVenta() {
        jTextFieldRucVenta.setText("");
        jTextFieldNombreClienteVEnta.setText("");
        jTextFieldTelefonoVenta.setText("");
        jTextFieldDireccionClienteVenta.setText("");
        jTextFieldOrigenCV.setText("");
    }

    private void Factura() {
        try {
            int id = Vdao.IdVenta();
            FileOutputStream archivo;
            File file = new File("src/pdf/venta" + id + ".pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            Image img = Image.getInstance("src/img/logo_pdf.png");

            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            fecha.add(Chunk.NEWLINE);
            Date date = new Date();
            fecha.add("Factura:" + id + "1\n" + "Fecha: " + new SimpleDateFormat("dd-mm-yyyy").format(date) + "\n\n");

            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] ColumnaEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);

            Encabezado.addCell(img);
            String ruc = jTextFieldRucDatos.getText();
            String nomb = jTextFieldNombreDatos.getText();;
            String telf = jTextFieldTelefonoDatos.getText();
            String dir = jTextFieldDireccionDatos.getText();
            String raz = jTextFieldRazonDatos.getText();

            Encabezado.addCell("");
            Encabezado.addCell("Ruc: " + ruc + "\nNombre: " + nomb + "\nTelefono: " + telf + "\nDireccion: " + dir + "\nrazon: " + raz);
            Encabezado.addCell(fecha);
            doc.add(Encabezado);

            Paragraph cli = new Paragraph();
            cli.add(Chunk.NEWLINE);
            cli.add("Datos de los clientes" + "\n\n");
            doc.add(cli);
            //Cliente
            PdfPTable tablaCli = new PdfPTable(4);
            tablaCli.setWidthPercentage(100);
            tablaCli.getDefaultCell().setBorder(0);
            float[] ColumnaCli = new float[]{20f, 50f, 30f, 40f};
            tablaCli.setWidths(ColumnaCli);
            tablaCli.setHorizontalAlignment(Element.ALIGN_LEFT);
            //titulos en celdas
            PdfPCell cl1 = new PdfPCell(new Phrase("Dni/Ruc", negrita));
            PdfPCell cl2 = new PdfPCell(new Phrase("Nombre", negrita));
            PdfPCell cl3 = new PdfPCell(new Phrase("Telefono", negrita));
            PdfPCell cl4 = new PdfPCell(new Phrase("Direccion", negrita));
            cl1.setBorder(0);
            cl2.setBorder(0);
            cl3.setBorder(0);
            cl4.setBorder(0);
            tablaCli.addCell(cl1);
            tablaCli.addCell(cl2);
            tablaCli.addCell(cl3);
            tablaCli.addCell(cl4);
            tablaCli.addCell(jTextFieldRucVenta.getText());
            tablaCli.addCell(jTextFieldNombreClienteVEnta.getText());
            tablaCli.addCell(jTextFieldTelefonoVenta.getText());
            tablaCli.addCell(jTextFieldDireccionClienteVenta.getText());

            doc.add(tablaCli);
            //productos
            PdfPTable tablaPro = new PdfPTable(4);
            tablaPro.setWidthPercentage(100);
            tablaPro.getDefaultCell().setBorder(0);
            float[] ColumnaPro = new float[]{10f, 50f, 15f, 20f};
            tablaPro.setWidths(ColumnaPro);
            tablaPro.setHorizontalAlignment(Element.ALIGN_LEFT);
            //titulos en celdas
            PdfPCell pro1 = new PdfPCell(new Phrase("Cantidad", negrita));
            PdfPCell pro2 = new PdfPCell(new Phrase("Descripcion", negrita));
            PdfPCell pro3 = new PdfPCell(new Phrase("Precio U.", negrita));
            PdfPCell pro4 = new PdfPCell(new Phrase("Precio T.", negrita));
            //quitando Borde
            pro1.setBorder(0);
            pro2.setBorder(0);
            pro3.setBorder(0);
            pro4.setBorder(0);
            pro1.setBackgroundColor(BaseColor.DARK_GRAY);
            pro2.setBackgroundColor(BaseColor.DARK_GRAY);
            pro3.setBackgroundColor(BaseColor.DARK_GRAY);
            pro4.setBackgroundColor(BaseColor.DARK_GRAY);
            tablaPro.addCell(pro1);
            tablaPro.addCell(pro2);
            tablaPro.addCell(pro3);
            tablaPro.addCell(pro4);

            for (int i = 0; i < jTableVenta.getRowCount(); i++) {
                String Cantidad = jTableVenta.getValueAt(i, 2).toString();
                String producto = jTableVenta.getValueAt(i, 1).toString();
                String precio = jTableVenta.getValueAt(i, 3).toString();
                String total = jTableVenta.getValueAt(i, 4).toString();
                tablaPro.addCell(Cantidad);
                tablaPro.addCell(producto);
                tablaPro.addCell(precio);
                tablaPro.addCell(total);
            }
            doc.add(tablaPro);

            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Total a pagar:" + Totalpagar);
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);

            Paragraph firma = new Paragraph();
            firma.add(Chunk.NEWLINE);
            firma.add("Cancelacion Y Firma\n\n");
            firma.add("------------------------");
            firma.setAlignment(Element.ALIGN_CENTER);
            doc.add(firma);

            Paragraph mensaje = new Paragraph();
            mensaje.add(Chunk.NEWLINE);
            mensaje.add("Gracias por su compra");
            mensaje.setAlignment(Element.ALIGN_CENTER);
            doc.add(mensaje);

            doc.close();
            archivo.close();
            //para abrir el pdF
            Desktop.getDesktop().open(file);
        } catch (DocumentException | IOException e) {
            System.out.println(e);
        }
    }

    private void Boleta() {
        try {
            int id = Vdao.IdVenta();
            FileOutputStream archivo;
            File file = new File("src/boleta/bventa" + id + ".pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            Image img = Image.getInstance("src/img/logo_pdf.png");

            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            fecha.add(Chunk.NEWLINE);
            Date date = new Date();
            fecha.add("Boleta:" + id + "1\n" + "Fecha: " + new SimpleDateFormat("dd-mm-yyyy").format(date) + "\n\n");

            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] ColumnaEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);

            Encabezado.addCell(img);
            String ruc = jTextFieldRucDatos.getText();
            String nomb = jTextFieldNombreDatos.getText();;
            String telf = jTextFieldTelefonoDatos.getText();
            String dir = jTextFieldDireccionDatos.getText();
            String raz = jTextFieldRazonDatos.getText();

            Encabezado.addCell("");
            Encabezado.addCell("Ruc: " + ruc + "\nNombre: " + nomb + "\nTelefono: " + telf + "\nDireccion: " + dir + "\nrazon: " + raz);
            Encabezado.addCell(fecha);
            doc.add(Encabezado);

            Paragraph cli = new Paragraph();
            cli.add(Chunk.NEWLINE);
            cli.add("Datos de los clientes" + "\n\n");
            doc.add(cli);
            //Cliente
            PdfPTable tablaCli = new PdfPTable(4);
            tablaCli.setWidthPercentage(100);
            tablaCli.getDefaultCell().setBorder(0);
            float[] ColumnaCli = new float[]{20f, 50f, 30f, 40f};
            tablaCli.setWidths(ColumnaCli);
            tablaCli.setHorizontalAlignment(Element.ALIGN_LEFT);
            //titulos en celdas
            PdfPCell cl1 = new PdfPCell(new Phrase("Dni/Ruc", negrita));
            PdfPCell cl2 = new PdfPCell(new Phrase("Nombre", negrita));
            PdfPCell cl3 = new PdfPCell(new Phrase("Telefono", negrita));
            PdfPCell cl4 = new PdfPCell(new Phrase("Direccion", negrita));
            cl1.setBorder(0);
            cl2.setBorder(0);
            cl3.setBorder(0);
            cl4.setBorder(0);
            tablaCli.addCell(cl1);
            tablaCli.addCell(cl2);
            tablaCli.addCell(cl3);
            tablaCli.addCell(cl4);
            tablaCli.addCell(jTextFieldRucVenta.getText());
            tablaCli.addCell(jTextFieldNombreClienteVEnta.getText());
            tablaCli.addCell(jTextFieldTelefonoVenta.getText());
            tablaCli.addCell(jTextFieldDireccionClienteVenta.getText());

            doc.add(tablaCli);
            //productos
            PdfPTable tablaPro = new PdfPTable(4);
            tablaPro.setWidthPercentage(100);
            tablaPro.getDefaultCell().setBorder(0);
            float[] ColumnaPro = new float[]{10f, 50f, 15f, 20f};
            tablaPro.setWidths(ColumnaPro);
            tablaPro.setHorizontalAlignment(Element.ALIGN_LEFT);
            //titulos en celdas
            PdfPCell pro1 = new PdfPCell(new Phrase("Cantidad", negrita));
            PdfPCell pro2 = new PdfPCell(new Phrase("Descripcion", negrita));
            PdfPCell pro3 = new PdfPCell(new Phrase("Precio U.", negrita));
            PdfPCell pro4 = new PdfPCell(new Phrase("Precio T.", negrita));
            //quitando Borde
            pro1.setBorder(0);
            pro2.setBorder(0);
            pro3.setBorder(0);
            pro4.setBorder(0);
            pro1.setBackgroundColor(BaseColor.DARK_GRAY);
            pro2.setBackgroundColor(BaseColor.DARK_GRAY);
            pro3.setBackgroundColor(BaseColor.DARK_GRAY);
            pro4.setBackgroundColor(BaseColor.DARK_GRAY);
            tablaPro.addCell(pro1);
            tablaPro.addCell(pro2);
            tablaPro.addCell(pro3);
            tablaPro.addCell(pro4);

            for (int i = 0; i < jTableVenta.getRowCount(); i++) {
                String Cantidad = jTableVenta.getValueAt(i, 2).toString();
                String producto = jTableVenta.getValueAt(i, 1).toString();
                String precio = jTableVenta.getValueAt(i, 3).toString();
                String total = jTableVenta.getValueAt(i, 4).toString();
                tablaPro.addCell(Cantidad);
                tablaPro.addCell(producto);
                tablaPro.addCell(precio);
                tablaPro.addCell(total);
            }
            doc.add(tablaPro);

            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Total a pagar:" + Totalpagar);
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);

            Paragraph firma = new Paragraph();
            firma.add(Chunk.NEWLINE);
            firma.add("Cancelacion Y Firma\n\n");
            firma.add("------------------------");
            firma.setAlignment(Element.ALIGN_CENTER);
            doc.add(firma);

            Paragraph mensaje = new Paragraph();
            mensaje.add(Chunk.NEWLINE);
            mensaje.add("Gracias por su compra");
            mensaje.setAlignment(Element.ALIGN_CENTER);
            doc.add(mensaje);

            doc.close();
            archivo.close();
            //para abrir el pdF
            Desktop.getDesktop().open(file);
        } catch (DocumentException | IOException e) {
            System.out.println(e);
        }
    }
}
