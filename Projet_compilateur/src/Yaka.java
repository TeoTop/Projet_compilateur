/* Generated By:JavaCC: Do not edit this line. Yaka.java */
public class Yaka implements YakaConstants {
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
                                output = Ecriture.ouvrir(fichierOut);
                                try {
                                        tmp = new java.io.FileInputStream(temp);
                                } catch (java.io.FileNotFoundException e) {
                                        e.printStackTrace();
                                }
                                output = Ecriture.ouvrir(fichierOut);
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
                YakaTokenManager.yvm.entete();
    jj_consume_token(PROGRAMME);
    jj_consume_token(ident);
    bloc();
    jj_consume_token(FPROGRAMME);
                YakaTokenManager.yvm.queue();
  }

  static final public void bloc() throws ParseException {
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CONST:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      declConst();
    }
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VAR:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      declVar();
    }
         YakaTokenManager.yvm.ouvrePrinc(YakaTokenManager.tabident.nbVar() * 2);
    suiteInstr();
  }

  static final public void declConst() throws ParseException {
    jj_consume_token(CONST);
    defConst();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 53:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_3;
      }
      jj_consume_token(53);
      defConst();
    }
    jj_consume_token(54);
  }

  static final public void defConst() throws ParseException {
    jj_consume_token(ident);
        YakaTokenManager.declaration.ajoutNomConstante(YakaTokenManager.identLu);
    jj_consume_token(EGAL);
    valConst();
  }

  static final public void valConst() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case entier:
      jj_consume_token(entier);
        YakaTokenManager.declaration.ajoutConstanteParEntier(YakaTokenManager.entierLu);
      break;
    case SUBNEG:
      jj_consume_token(SUBNEG);
      jj_consume_token(entier);
        YakaTokenManager.declaration.ajoutConstanteParEntier(YakaTokenManager.entierLu*-1);
      break;
    case ident:
      jj_consume_token(ident);
        YakaTokenManager.declaration.ajoutConstanteParConstante(YakaTokenManager.identLu);
      break;
    case VRAI:
      jj_consume_token(VRAI);
        YakaTokenManager.declaration.ajoutConstanteParBooleen(-1);
      break;
    case FAUX:
      jj_consume_token(FAUX);
        YakaTokenManager.declaration.ajoutConstanteParBooleen(0);
      break;
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void declVar() throws ParseException {
    jj_consume_token(VAR);
    type();
    jj_consume_token(ident);
        YakaTokenManager.declaration.ajoutNomVariable(YakaTokenManager.identLu);
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 53:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_4;
      }
      jj_consume_token(53);
      jj_consume_token(ident);
        YakaTokenManager.declaration.ajoutNomVariableSecondaire(YakaTokenManager.identLu);
    }
    jj_consume_token(54);
  }

  static final public void type() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ENTIER:
      jj_consume_token(ENTIER);
                YakaTokenManager.declaration.ajoutVariableParTYPE("ENTIER");
      break;
    case BOOLEEN:
      jj_consume_token(BOOLEEN);
                YakaTokenManager.declaration.ajoutVariableParTYPE("BOOLEEN");
      break;
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

/*
 * Syntaxe des instructions.
 */
  static final public void suiteInstr() throws ParseException {
    instruction();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 54:
        ;
        break;
      default:
        jj_la1[6] = jj_gen;
        break label_5;
      }
      jj_consume_token(54);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ECRIRE:
      case LIRE:
      case ALALIGNE:
      case ident:
        instruction();
        break;
      default:
        jj_la1[7] = jj_gen;
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
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void affectation() throws ParseException {
    jj_consume_token(ident);
                String id = YakaTokenManager.identLu;
    jj_consume_token(EGAL);
    expression();
                YakaTokenManager.expression.store(id);
        /* Vider les piles pour évaluer une autre expression */
        YakaTokenManager.expression.clearType();
        YakaTokenManager.expression.clearOp();
  }

  static final public void lecture() throws ParseException {
    jj_consume_token(LIRE);
    jj_consume_token(55);
    jj_consume_token(ident);
    jj_consume_token(56);
                YakaTokenManager.expression.lire(YakaTokenManager.identLu);
  }

  static final public void ecriture() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ECRIRE:
      jj_consume_token(ECRIRE);
      jj_consume_token(55);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VRAI:
      case FAUX:
      case NON:
      case SUBNEG:
      case entier:
      case ident:
      case 55:
        expression();
                YakaTokenManager.expression.ecrire();
        break;
      case chaine:
        jj_consume_token(chaine);
                YakaTokenManager.yvm.ecrireChaine(YakaTokenManager.chaineLue);
        break;
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      jj_consume_token(56);
      break;
    case ALALIGNE:
      jj_consume_token(ALALIGNE);
                YakaTokenManager.yvm.aLaLigne();
      break;
    default:
      jj_la1[10] = jj_gen;
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
        YakaTokenManager.expression.testRel();
        YakaTokenManager.expression.executerOpRel();
      break;
    default:
      jj_la1[11] = jj_gen;
      ;
    }

  }

  static final public void simpleExpr() throws ParseException {
    terme();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OU:
      case ADD:
      case SUBNEG:
        ;
        break;
      default:
        jj_la1[12] = jj_gen;
        break label_6;
      }
      opAdd();
      terme();
           YakaTokenManager.expression.testAdd();
           YakaTokenManager.expression.executerOpAdd();
    }
  }

  static final public void terme() throws ParseException {
    facteur();
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ET:
      case MUL:
      case DIV:
        ;
        break;
      default:
        jj_la1[13] = jj_gen;
        break label_7;
      }
      opMul();
      facteur();
           YakaTokenManager.expression.testMul();
           YakaTokenManager.expression.executerOpMul();
    }
  }

  static final public void facteur() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VRAI:
    case FAUX:
    case entier:
    case ident:
    case 55:
      primaire();
      break;
    case NON:
    case SUBNEG:
      opNeg();
      primaire();
           YakaTokenManager.expression.testNeg();
          YakaTokenManager.expression.executerOpNeg();
      break;
    default:
      jj_la1[14] = jj_gen;
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
    case 55:
      jj_consume_token(55);
      expression();
      jj_consume_token(56);
      break;
    default:
      jj_la1[15] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void valeur() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case entier:
      jj_consume_token(entier);
                YakaTokenManager.expression.empileType("entier");
                YakaTokenManager.yvm.iconst(YakaTokenManager.entierLu);
      break;
    case ident:
      jj_consume_token(ident);
                YakaTokenManager.expression.empileTypeAvecIdent(YakaTokenManager.identLu);
                YakaTokenManager.expression.loadIdent(YakaTokenManager.identLu);
      break;
    case VRAI:
      jj_consume_token(VRAI);
                YakaTokenManager.expression.empileType("booleen");
                YakaTokenManager.yvm.iconst(-1);
      break;
    case FAUX:
      jj_consume_token(FAUX);
                YakaTokenManager.expression.empileType("booleen");
                YakaTokenManager.yvm.iconst(0);
      break;
    default:
      jj_la1[16] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void opRel() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EGAL:
      jj_consume_token(EGAL);
          YakaTokenManager.expression.empileOpera("=");
          YakaTokenManager.expression.setLastOpRel("=");
      break;
    case DIFF:
      jj_consume_token(DIFF);
          YakaTokenManager.expression.empileOpera("<>");
          YakaTokenManager.expression.setLastOpRel("<>");
      break;
    case INF:
      jj_consume_token(INF);
          YakaTokenManager.expression.empileOpera("<");
          YakaTokenManager.expression.setLastOpRel("<");
      break;
    case INFEGAL:
      jj_consume_token(INFEGAL);
          YakaTokenManager.expression.empileOpera("<=");
          YakaTokenManager.expression.setLastOpRel("<=");
      break;
    case SUP:
      jj_consume_token(SUP);
          YakaTokenManager.expression.empileOpera(">");
          YakaTokenManager.expression.setLastOpRel(">");
      break;
    case SUPEGAL:
      jj_consume_token(SUPEGAL);
          YakaTokenManager.expression.empileOpera(">=");
          YakaTokenManager.expression.setLastOpRel(">=");
      break;
    default:
      jj_la1[17] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void opAdd() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ADD:
      jj_consume_token(ADD);
          YakaTokenManager.expression.empileOpera("+");
          YakaTokenManager.expression.setLastOpAdd("+");
      break;
    case SUBNEG:
      jj_consume_token(SUBNEG);
          YakaTokenManager.expression.empileOpera("-");
          YakaTokenManager.expression.setLastOpAdd("-");
      break;
    case OU:
      jj_consume_token(OU);
          YakaTokenManager.expression.empileOpera("ou");
          YakaTokenManager.expression.setLastOpAdd("OR");
      break;
    default:
      jj_la1[18] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void opMul() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case MUL:
      jj_consume_token(MUL);
        YakaTokenManager.expression.empileOpera("*");
          YakaTokenManager.expression.setLastOpMul("*");
      break;
    case DIV:
      jj_consume_token(DIV);
        YakaTokenManager.expression.empileOpera("/");
          YakaTokenManager.expression.setLastOpMul("/");
      break;
    case ET:
      jj_consume_token(ET);
          YakaTokenManager.expression.empileOpera("et");
          YakaTokenManager.expression.setLastOpMul("AND");
      break;
    default:
      jj_la1[19] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void opNeg() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SUBNEG:
      jj_consume_token(SUBNEG);
          YakaTokenManager.expression.empileOpera("-");
          YakaTokenManager.expression.setLastOpNeg("-");
      break;
    case NON:
      jj_consume_token(NON);
          YakaTokenManager.expression.empileOpera("non");
          YakaTokenManager.expression.setLastOpNeg("NOT");
      break;
    default:
      jj_la1[20] = jj_gen;
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
  static final private int[] jj_la1 = new int[21];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x80000,0x200,0x0,0x120000,0x0,0x8100,0x0,0x0,0x0,0x1120000,0x0,0x0,0x400000,0x800000,0x1120000,0x120000,0x120000,0x0,0x400000,0x800000,0x1000000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x200000,0xa0010,0x200000,0x0,0x400000,0x80007,0x80007,0x9a0010,0x5,0xfc00,0x18,0xc0,0x8a0010,0x8a0000,0xa0000,0xfc00,0x18,0xc0,0x10,};
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
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
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
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
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
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(YakaTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[57];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 21; i++) {
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
    for (int i = 0; i < 57; i++) {
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
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
