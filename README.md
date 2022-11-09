# Compito_TPSIT_9_11_22
Compito Teriaca Mattia 

COMANDO -->

Realizzare un servizio di vendita biglietti tramite una connessione Client/Server utilizzando i socket TCP in Java.
Il server all'avvio istanzia il numero di biglietti disponibili alla vendita.

Il vari clients, una volta connessi, potranno inviare al server i seguenti tipi di richieste:
'D' -> richiesta disponibilità
'A' ->  acquista biglietto
'Q' -> disconnessione

Il server risponde alla richiesta 'D' con "Disponibili n biglietti" dove n è il valore del contatore;
Il server risponde alla richiesta 'A' con "Biglietto acquistato" o "Biglietti esauriti" in base alla disponibilità;
Inoltre per ogni acquisto il server deve decrementare il numero di biglietti disponibili (se maggiore di zero)

BONUS
All'esaurimento dei biglietti il server notifica a tutti i clients connessi l'impossibilità di effettuare ulteriori acquisti
