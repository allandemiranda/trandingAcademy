//+------------------------------------------------------------------+
//|                                                    GetValue2.mq5 |
//|                                      Copyright 2022, Allan Silva |
//+------------------------------------------------------------------+
#property copyright "Copyright 2022, Allan Silva"
#property link      "https://www.mql5.com"
#property version   "1.00"
#property script_show_inputs

input string Address = "127.0.0.1";
input int    Port    = 900;
int    socket ;

//+------------------------------------------------------------------+
//| Sending information to the server                                |
//+------------------------------------------------------------------+
bool HTTPSend(int socket, string request) {
   char req[];
   int  len = StringToCharArray(request, req) - 1;
   if(len < 0) {
      return(false);
   }
   return(SocketSend(socket, req, len) == len);
}
//+------------------------------------------------------------------+
//| Expert initialization function                                   |
//+------------------------------------------------------------------+
int OnInit() {
//--- creating the socket
   socket  = SocketCreate();
   if(socket != INVALID_HANDLE) {
      if(SocketConnect(socket, Address, Port, 1000)) {
         Print("Conectado a ", Address, ":", Port);
         //--- para testes no final de semana
         //for(int i = 0; i < 500; ++i) {
         //double bid = rand()/10;
         //   if(!HTTPSend(socket, bid) || !HTTPSend(socket, "\n") ) {
         //      Print("Falha ao enviar solicitação GET, erro ", GetLastError());
         //   }
         //   Sleep(2000);
         //}
         //---
      } else {
         Print("Failed connection ", Address, ":", Port, ", error ", GetLastError());
         SocketClose(socket);
         return(INIT_FAILED);
      }
   } else {
      Print("Can't create the socket, error ", GetLastError());
      return(INIT_FAILED);
   }
//---
   return(INIT_SUCCEEDED);
}

//+------------------------------------------------------------------+
//| Expert deinitialization function                                 |
//+------------------------------------------------------------------+
void OnDeinit(const int reason) {
//--- destroy socket
   SocketClose(socket);
   EventKillTimer();
}

//+------------------------------------------------------------------+
//| Expert tick function                                             |
//+------------------------------------------------------------------+
void OnTick() {
//--- sending the price to the server
   MqlTick Latest_Price;
   SymbolInfoTick(Symbol(), Latest_Price);
   if(!HTTPSend(socket, Latest_Price.bid) || !HTTPSend(socket, "\n")) {
      Print("Fail send the price, erro ", GetLastError());
   }
}
//+------------------------------------------------------------------+