#!/bin/bash

typeset PROPERTIES_FILE=$1

"C:\Program Files\Java\jdk-17.0.2\bin\java.exe" \
  -classpath "C:\Users\allan\Documents\trandingAcademy\target\trandingAcademy-1.0-SNAPSHOT-bin\trandingAcademy-1.0-SNAPSHOT\libs\*;C:\Users\allan\Documents\trandingAcademy\target\trandingAcademy-1.0-SNAPSHOT-bin\trandingAcademy-1.0-SNAPSHOT\etc" \
  -Dlog4j.configurationFile=file:"C:\Users\allan\Documents\trandingAcademy\src\main\resources\etc\log4j2.xml" \
  -XX:+HeapDumpOnOutOfMemoryError \
  -Xmx1024m \
  br.bti.allandemiranda.forex.processor.TradingViewAnalysisProcessor \
  -propertiesFile $PROPERTIES_FILE
