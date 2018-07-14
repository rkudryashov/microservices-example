for %%x in (
        config-server
        eureka-server
        ui-gateway
        greeting-ui
        some-service
       ) do (
         cd %%x
         start ..\gradlew.bat bootRun
         cd ..
       )