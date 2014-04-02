/* Generated By:JavaCC: Do not edit this line. Yaka.java */
public class Yaka implements YakaConstants {
		/*
		 * Variables statics globales permettant d'acc�der aux classes sp�cifi�es facilement
		 * depuis YAKA.  
		 */
        public static Declaration declaration= new Declaration();
        public static TabIdent tabident = new TabIdent();
        public static Expression expression = new Expression();
        public static YVM yvm = new YVMasm();
        public static Conditionnelle condition = new Conditionnelle();
        public static Fonction fonction = new Fonction();
        public static Iteration iteration = new Iteration();
        public static Token token1 = new Token();

        public static void main(String args[]) {
                Yaka analyseur;
                java.io.InputStream input;
                java.io.OutputStream output;
                java.io.File temp;
                java.io.InputStream tmp = null;
                String fichierOut;
                if (args.length==2) {
                        System.out.print(args[0] + ": ");
                        try {
                                input = new java.io.FileInputStream(args[0] + ".yaka"); /* avant : args.lentgh -1 */
                                fichierOut = args[1] + ".asm";
                        } catch (java.io.FileNotFoundException e) {
                                System.out.println("Fichier introuvable.");
                                return;
                        }
                } else if (args.length==1) {
                        fichierOut = args[0] + ".asm";
                        System.out.println("Lecture sur l'entree standard...");
                        input = System.in;
                } else {
                        System.out.println("Usage: java Yaka [fichier entr\u00c3\u00a9e] [fichier sortie] ou java Yaka [fichier sortie]");
                        return;
                }
                try {
                        analyseur = new Yaka(input);
                        analyseur.analyse();
                        System.out.println("analyse syntaxique reussie!");
                        temp = new java.io.File("yvm.tmp");
                        if (Erreur.err) {
                                System.out.println("Compilation impossible");
                                System.out.println("La g\u00e9n\u00e9ration du fichier a \u00e9chou\u00e9");
                        }
                        else {
                                yvm.recopierEntete(fichierOut);
                                try {
                                        tmp = new java.io.FileInputStream(temp);
                                } catch (java.io.FileNotFoundException e) {
                                        e.printStackTrace();
                                }
                                output = Ecriture.ouvrirSuite(fichierOut);
                                Ecriture.ecrireFichier(output,tmp);
                                Ecriture.fermer(output);
                                Lecture.fermer(tmp);
                                System.out.println("Le fichier `" + fichierOut + "` a \u00e9t\u00e9 g\u00e9n\u00e9r\u00e9 avec succ\u00e8s");
                        }
                        temp.delete(); /* suppression du ficher temporaire */
                } catch (ParseException e) {
                        String msg = e.getMessage();
                        msg = msg.substring(0,msg.indexOf("\u005cn"));
                        System.out.println("Erreur de syntaxe : "+msg);
                }
        }

/**************************************/
/********debut de la grammaire ********/
/**************************************/
  static final public void analyse() throws ParseException {
    jj_consume_token(PROGRAMME);
    jj_consume_token(ident);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BOOLEEN:
      case ENTIER:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      declFonction();
    }
    jj_consume_token(PRINCIPAL);
                yvm.debut();
                yvm.ecrireEtiqu("main");
    bloc();
    jj_consume_token(FPRINCIPAL);
    jj_consume_token(FPROGRAMME);
                yvm.queue();
  }

  static final public void declFonction() throws ParseException {
                fonction.inFunction(true);
    typeF();
    jj_consume_token(FONCTION);
    jj_consume_token(ident);
                String ident = YakaTokenManager.identLu;
                yvm.ecrireEtiqu(ident);
    paramForms();
                tabident.rangeParam();
    bloc();
    jj_consume_token(FFONCTION);
                declaration.ajoutNomFonction(ident);
                fonction.fermeBloc(ident);
                tabident.clearFun();
                fonction.inFunction(false);
  }

  static final public void typeF() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ENTIER:
      jj_consume_token(ENTIER);
                declaration.ajoutFonction(YakaConstants.ENTIER);
      break;
    case BOOLEEN:
      jj_consume_token(BOOLEEN);
                declaration.ajoutFonction(YakaConstants.BOOLEEN);
      break;
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void paramForms() throws ParseException {
    jj_consume_token(54);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOLEEN:
    case ENTIER:
      paramForm();
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 55:
          ;
          break;
        default:
          jj_la1[2] = jj_gen;
          break label_2;
        }
        jj_consume_token(55);
        paramForm();
      }
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
    jj_consume_token(56);
  }

  static final public void paramForm() throws ParseException {
    typeP();
    jj_consume_token(ident);
                declaration.addParam(YakaTokenManager.identLu);
  }

  static final public void typeP() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ENTIER:
      jj_consume_token(ENTIER);
                declaration.ajoutParam(YakaConstants.ENTIER);
      break;
    case BOOLEEN:
      jj_consume_token(BOOLEEN);
                declaration.ajoutParam(YakaConstants.BOOLEEN);
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void bloc() throws ParseException {
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CONST:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_3;
      }
      declConst();
    }
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VAR:
        ;
        break;
      default:
        jj_la1[6] = jj_gen;
        break label_4;
      }
      declVar();
    }
         yvm.ouvbloc(tabident.nbVar() * 2);
    suiteInstr();
  }

  static final public void declConst() throws ParseException {
    jj_consume_token(CONST);
    defConst();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 55:
        ;
        break;
      default:
        jj_la1[7] = jj_gen;
        break label_5;
      }
      jj_consume_token(55);
      defConst();
    }
    jj_consume_token(57);
  }

  static final public void defConst() throws ParseException {
    jj_consume_token(ident);
        declaration.ajoutNomConstante(YakaTokenManager.identLu);
    jj_consume_token(EGAL);
    valConst();
  }

  static final public void valConst() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case entier:
      jj_consume_token(entier);
        declaration.ajoutConstanteParEntier(YakaTokenManager.entierLu);
      break;
    case SUBNEG:
      jj_consume_token(SUBNEG);
      jj_consume_token(entier);
        declaration.ajoutConstanteParEntier(YakaTokenManager.entierLu*-1);
      break;
    case ident:
      jj_consume_token(ident);
        declaration.ajoutConstanteParConstante(YakaTokenManager.identLu);
      break;
    case VRAI:
      jj_consume_token(VRAI);
        declaration.ajoutConstanteParBooleen(-1);
      break;
    case FAUX:
      jj_consume_token(FAUX);
        declaration.ajoutConstanteParBooleen(0);
      break;
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void declVar() throws ParseException {
    jj_consume_token(VAR);
    type();
    jj_consume_token(ident);
        declaration.ajoutNomVariable(YakaTokenManager.identLu);
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 55:
        ;
        break;
      default:
        jj_la1[9] = jj_gen;
        break label_6;
      }
      jj_consume_token(55);
      jj_consume_token(ident);
        declaration.ajoutNomVariableSecondaire(YakaTokenManager.identLu);
    }
    jj_consume_token(57);
  }

  static final public void type() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ENTIER:
      jj_consume_token(ENTIER);
                declaration.ajoutVariableParTYPE(YakaConstants.ENTIER);
      break;
    case BOOLEEN:
      jj_consume_token(BOOLEEN);
                declaration.ajoutVariableParTYPE(YakaConstants.BOOLEEN);
      break;
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

/*
 * Syntaxe des instructions.
 */
  static final public void suiteInstr() throws ParseException {
    instruction();
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 57:
        ;
        break;
      default:
        jj_la1[11] = jj_gen;
        break label_7;
      }
      jj_consume_token(57);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SI:
      case RETOURNE:
      case TANTQUE:
      case ECRIRE:
      case LIRE:
      case ALALIGNE:
      case ident:
        instruction();
        break;
      default:
        jj_la1[12] = jj_gen;
        ;
      }
    }
  }

  static final public void instruction() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ident:
      affectation();
      break;
    case LIRE:
      lecture();
      break;
    case ECRIRE:
    case ALALIGNE:
      ecriture();
      break;
    case SI:
      conditionnelle();
      break;
    case TANTQUE:
      iteration();
      break;
    case RETOURNE:
      retourne();
      break;
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void retourne() throws ParseException {
    jj_consume_token(RETOURNE);
    expression();
                fonction.testInFunc();
                expression.testTypeExprFunc();
                fonction.returnFun();
  }

  static final public void iteration() throws ParseException {
    jj_consume_token(TANTQUE);
                iteration.addTantQue();
                iteration.ecrireFaire();
    expression();
                expression.testBool();
                iteration.ecrireIffauxFait();
    jj_consume_token(FAIRE);
    suiteInstr();
                iteration.ecrireGotoFaire();
    jj_consume_token(FAIT);
                iteration.ecrireFait();
                iteration.removeTantQue();
  }

  static final public void conditionnelle() throws ParseException {
    jj_consume_token(SI);
                condition.addSi();
    expression();
                expression.testBool();
                condition.ecrireIffauxSinon();
    jj_consume_token(ALORS);
    suiteInstr();
                condition.ecrireGotoFSI();
                condition.ecrireSinon();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SINON:
      jj_consume_token(SINON);
      suiteInstr();
      break;
    default:
      jj_la1[14] = jj_gen;
      ;
    }
    jj_consume_token(FSI);
                condition.ecrireFsi();
                condition.removeSi();
  }

  static final public void affectation() throws ParseException {
    jj_consume_token(ident);
                String id = YakaTokenManager.identLu;
    jj_consume_token(EGAL);
    expression();
                expression.store(id);
                /* Vider les piles pour évaluer une autre expression */
                expression.clearType();
                expression.clearOp();
  }

  static final public void lecture() throws ParseException {
    jj_consume_token(LIRE);
    jj_consume_token(54);
    jj_consume_token(ident);
    jj_consume_token(56);
                expression.lire(YakaTokenManager.identLu);
  }

  static final public void ecriture() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ECRIRE:
      jj_consume_token(ECRIRE);
      jj_consume_token(54);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VRAI:
      case FAUX:
      case NON:
      case SUBNEG:
      case entier:
      case ident:
      case 54:
        expression();
                expression.ecrire();
        break;
      case chaine:
        jj_consume_token(chaine);
                yvm.ecrireChaine(YakaTokenManager.chaineLue);
        break;
      default:
        jj_la1[15] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      jj_consume_token(56);
      break;
    case ALALIGNE:
      jj_consume_token(ALALIGNE);
                yvm.aLaLigne();
      break;
    default:
      jj_la1[16] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

/*
 * Expression .
 */
  static final public void expression() throws ParseException {
    simpleExpr();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EGAL:
    case DIFF:
    case INF:
    case INFEGAL:
    case SUP:
    case SUPEGAL:
      opRel();
      simpleExpr();
        expression.testRel();
        expression.executerOpRel();
      break;
    default:
      jj_la1[17] = jj_gen;
      ;
    }

  }

  static final public void simpleExpr() throws ParseException {
    terme();
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OU:
      case ADD:
      case SUBNEG:
        ;
        break;
      default:
        jj_la1[18] = jj_gen;
        break label_8;
      }
      opAdd();
      terme();
           expression.testAdd();
           expression.executerOpAdd();
    }
  }

  static final public void terme() throws ParseException {
    facteur();
    label_9:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ET:
      case MUL:
      case DIV:
        ;
        break;
      default:
        jj_la1[19] = jj_gen;
        break label_9;
      }
      opMul();
      facteur();
           expression.testMul();
           expression.executerOpMul();
    }
  }

  static final public void facteur() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VRAI:
    case FAUX:
    case entier:
    case ident:
    case 54:
      primaire();
      break;
    case NON:
    case SUBNEG:
      opNeg();
      primaire();
           expression.testNeg();
          expression.executerOpNeg();
      break;
    default:
      jj_la1[20] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void primaire() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VRAI:
    case FAUX:
    case entier:
    case ident:
      valeur();
      break;
    case 54:
      jj_consume_token(54);
      expression();
      jj_consume_token(56);
      break;
    default:
      jj_la1[21] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void valeur() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case entier:
      jj_consume_token(entier);
                expression.empileType(YakaConstants.ENTIER);
                yvm.iconst(YakaTokenManager.entierLu);
      break;
    case ident:
      jj_consume_token(ident);
                expression.empileTypeAvecIdent(YakaTokenManager.identLu);
                expression.loadIdent(YakaTokenManager.identLu);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 54:
                        String id = YakaTokenManager.identLu;
                        tabident.identIsFunction(id);
                        tabident.newFun();
                        fonction.newFun(id);
                        yvm.reserveRetour();
        argumentsFonction();
                        tabident.testNbParam(id);
                        tabident.resetParamTest();
                        fonction.call();
                        fonction.empileTypeFun();
                        fonction.resetFun();
        break;
      default:
        jj_la1[22] = jj_gen;
        ;
      }
      break;
    case VRAI:
      jj_consume_token(VRAI);
                expression.empileType(YakaConstants.BOOLEEN);
                yvm.iconst(-1);
      break;
    case FAUX:
      jj_consume_token(FAUX);
                expression.empileType(YakaConstants.BOOLEEN);
                yvm.iconst(0);
      break;
    default:
      jj_la1[23] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void argumentsFonction() throws ParseException {
    jj_consume_token(54);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VRAI:
    case FAUX:
    case NON:
    case SUBNEG:
    case entier:
    case ident:
    case 54:
      expression();
                tabident.addArgument();
                expression.testParamFonc();
      label_10:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 55:
          ;
          break;
        default:
          jj_la1[24] = jj_gen;
          break label_10;
        }
        jj_consume_token(55);
        expression();
                tabident.addArgument();
                expression.testParamFonc();
      }
      break;
    default:
      jj_la1[25] = jj_gen;
      ;
    }
    jj_consume_token(56);
  }

  static final public void opRel() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EGAL:
      jj_consume_token(EGAL);
          expression.empileOpera(YakaConstants.EGAL);
      break;
    case DIFF:
      jj_consume_token(DIFF);
          expression.empileOpera(YakaConstants.DIFF);
      break;
    case INF:
      jj_consume_token(INF);
          expression.empileOpera(YakaConstants.INF);
      break;
    case INFEGAL:
      jj_consume_token(INFEGAL);
          expression.empileOpera(YakaConstants.INFEGAL);
      break;
    case SUP:
      jj_consume_token(SUP);
          expression.empileOpera(YakaConstants.SUP);
      break;
    case SUPEGAL:
      jj_consume_token(SUPEGAL);
          expression.empileOpera(YakaConstants.SUPEGAL);
      break;
    default:
      jj_la1[26] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void opAdd() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ADD:
      jj_consume_token(ADD);
          expression.empileOpera(YakaConstants.ADD);
      break;
    case SUBNEG:
      jj_consume_token(SUBNEG);
          expression.empileOpera(YakaConstants.SUBNEG);
      break;
    case OU:
      jj_consume_token(OU);
          expression.empileOpera(YakaConstants.OR);
      break;
    default:
      jj_la1[27] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void opMul() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case MUL:
      jj_consume_token(MUL);
        expression.empileOpera(YakaConstants.MUL);
      break;
    case DIV:
      jj_consume_token(DIV);
        expression.empileOpera(YakaConstants.DIV);
      break;
    case ET:
      jj_consume_token(ET);
          expression.empileOpera(YakaConstants.AND);
      break;
    default:
      jj_la1[28] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void opNeg() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SUBNEG:
      jj_consume_token(SUBNEG);
          expression.empileOpera(YakaConstants.SUBNEG);
      break;
    case NON:
      jj_consume_token(NON);
          expression.empileOpera(YakaConstants.NOT);
      break;
    default:
      jj_la1[29] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public YakaTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[30];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x8100,0x8100,0x0,0x8100,0x8100,0x100000,0x200,0x0,0x240000,0x0,0x8100,0x0,0xa2000,0xa2000,0x800,0x2240000,0x0,0x0,0x800000,0x1000000,0x2240000,0x240000,0x0,0x240000,0x0,0x2240000,0x0,0x800000,0x1000000,0x2000000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x800000,0x0,0x0,0x0,0x0,0x800000,0x140020,0x800000,0x0,0x2000000,0x10000e,0x10000e,0x0,0x740020,0xa,0x1f800,0x30,0x180,0x540020,0x540000,0x400000,0x140000,0x800000,0x540020,0x1f800,0x30,0x180,0x20,};
   }

  /** Constructor with InputStream. */
  public Yaka(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Yaka(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new YakaTokenManager(jj_input_stream);
    token1 = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 30; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token1 = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 30; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Yaka(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new YakaTokenManager(jj_input_stream);
    token1 = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 30; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token1 = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 30; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Yaka(YakaTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token1 = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 30; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(YakaTokenManager tm) {
    token_source = tm;
    token1 = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 30; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token1).next != null) token1 = token1.next;
    else token1 = token1.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token1.kind == kind) {
      jj_gen++;
      return token1;
    }
    token1 = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token1.next != null) token1 = token1.next;
    else token1 = token1.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token1;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token1;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token1.next) == null)
      return (jj_ntk = (token1.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[58];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 30; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 58; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token1, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
