// `.env.ts` is generated by the `npm run env` command
import env from './.env';

export const trasparenzaBaseUrl = '/trasparenza-atti-cat';
export const comuneBaseUrl = 'https://www.comune.fi.it';

export const sections = {
  deliberazioni: '/deliberazioni',
  provvedimentiDirigenziali: '/provvedimenti-dirigenziali',
  attiConsiglio: '/atti-consiglio',
  decreti: '/decreti',
  ordinanze: '/ordinanze'
};

export const environment = {
  production: true,
  displayMenu: false,
  maxDisplayedCharacters: 100,
  version: env.npm_package_version,
  serverUrl: '',
  minDeployYear: 2018,
  defaultLanguage: 'it-IT',
  supportedLanguages: [
    'it-IT',
    'en-US'
  ],
  endpoint: {
      get: {
          getAttoByTipoNumeroAnno: trasparenzaBaseUrl + '/getAttoByTipoNumeroAnno',
          uffici: trasparenzaBaseUrl + '/getUffici',
          relatori: trasparenzaBaseUrl + '/getRelatori',
          sottotipi: trasparenzaBaseUrl + '/getSottotipi',
          stati: trasparenzaBaseUrl + '/getStati',
          esiti: trasparenzaBaseUrl + '/getEsiti',
          assessori: trasparenzaBaseUrl + '/getAssessori',
          commissioni: trasparenzaBaseUrl + '/getCommissioni'
      },
      post: {
          searchAtti: trasparenzaBaseUrl + '/searchAtti',
          resetAtti: trasparenzaBaseUrl + '/resetAtti'
          
      }
  },
  queryParameters: {
    getAttoByTipoNumeroAnno: {
      tipo: 'tipo',
      numero: 'numero',
      anno: 'anno'
    }
  },
  breadcrumbs: {
    types: {
      attribute: 'competenze',
      section: {
        deliberazioni: {
          provvedimentiOrganiIndirizzoPolitico: 'G',
          lavori: 'C'
        }
      }
    }
  },
  atto: {
    tipo: {
      delibereGiunta: {codice: 'DG', descrizione: 'Delibere di Giunta', sezione: sections.deliberazioni},
      delibereConsiglio: {codice: 'DC', descrizione: 'Delibere di Consiglio', sezione: sections.deliberazioni},
      delibereIndirizzoGiunta: {codice: 'DIG', descrizione: 'Delibere di indirizzo di Giunta', sezione: sections.deliberazioni},
      deliberePerConsiglio: {codice: 'DPC', descrizione: 'Delibere per il Consiglio', sezione: sections.deliberazioni},
      delibereIndirizzoConsiglio: {codice: 'DIC', descrizione: 'Delibere di indirizzo di Consiglio', sezione: sections.deliberazioni},
      determinaDirigenziale: {codice: 'DD', descrizione: 'Determinazione Dirigenziale', sezione: sections.provvedimentiDirigenziali},
      determineLiquidazione: {codice: 'DL', descrizione: 'Determine di Liquidazione', sezione: sections.provvedimentiDirigenziali},
      ordineDelGiorno: {codice: 'ODG', descrizione: 'Ordine del Giorno', sezione: sections.attiConsiglio},
      mozione: {codice: 'MZ', descrizione: 'Mozione', sezione: sections.attiConsiglio},
      comunicazioni: {codice: 'COM', descrizione: 'Comunicazioni', sezione: sections.attiConsiglio},
      domandeAttualita: {codice: 'DAT', descrizione: 'Domande di attualita\'', sezione: sections.attiConsiglio},
      interrogazioni: {codice: 'INT', descrizione: 'Interrogazioni', sezione: sections.attiConsiglio},
      questionTime: {codice: 'QT', descrizione: 'Question time', sezione: sections.attiConsiglio},
      verbali: {codice: 'VERB', descrizione: 'Verbali', sezione: sections.attiConsiglio},
      risoluzioni: {codice: 'RIS', descrizione: 'Risoluzioni', sezione: sections.attiConsiglio},
      decreti: {codice: 'DEC', descrizione: 'Decreti', sezione: sections.decreti},
      ordinanze: {codice: 'ORD', descrizione: 'Ordinanze', sezione: sections.ordinanze},
      ordinanzeTSO: {codice: 'ORDT', descrizione: 'Ordinanze TSO', sezione: sections.ordinanze}
    }
  }
};

export const filters = {
  select: {
    deliberazioni: {
      tipoAtti: [
        environment.atto.tipo.delibereConsiglio,
        environment.atto.tipo.delibereGiunta,
        environment.atto.tipo.delibereIndirizzoGiunta,
        environment.atto.tipo.delibereIndirizzoConsiglio,
        environment.atto.tipo.deliberePerConsiglio
      ],
      giunta: {
        tipoAtti: [
          environment.atto.tipo.delibereGiunta,
          environment.atto.tipo.delibereIndirizzoGiunta
        ]
      },
      consiglio: {
        tipoAtti: [
          environment.atto.tipo.delibereConsiglio,
          environment.atto.tipo.delibereIndirizzoConsiglio,
          environment.atto.tipo.deliberePerConsiglio
        ]
      }
    },
    decreti: {
      tipoAtti: [
        environment.atto.tipo.decreti
      ]
    },
    ordinanze: {
      tipoAtti: [
        environment.atto.tipo.ordinanze,
        environment.atto.tipo.ordinanzeTSO
      ]
    },
    provvedimentiDirigenziali: {
      tipoAtti: [
        environment.atto.tipo.determinaDirigenziale,
        environment.atto.tipo.determineLiquidazione
      ]
    },
    attiConsiglio: {
      tipoAtti: [
	environment.atto.tipo.comunicazioni,
	environment.atto.tipo.domandeAttualita,
	environment.atto.tipo.interrogazioni,
        environment.atto.tipo.mozione,
        environment.atto.tipo.ordineDelGiorno,            
        environment.atto.tipo.questionTime,
        environment.atto.tipo.risoluzioni
      ]
    }
  }
};