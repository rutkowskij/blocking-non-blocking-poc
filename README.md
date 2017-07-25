Steps to reproduce:

1. Run blocking-service/BlockingServiceApplication (it will expose http://localhost:9000/routing endpoint - it sleeps 200ms in each request)
2. Run non-blocking-client/NonBlockingClientApplication, or one of the other non-blocking sub-projects (it will expose http://localhost:8000/client endpoint which call above blocking service)
3. Run gatling test - gatling-load-tests/mvn gatling:test

After test scenerio ~2min You have generated test report:
Please open the following file: PATH_TO_REPORT
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
