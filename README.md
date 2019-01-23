# WSO2-APi-Integrator

### Enterprise Integrator
	Downloaded, created proxy and REST API that tries to achieve these feature 
		JSON schema validation
		Transform the payload to XML
		Call backed SOAP service (wiremock stub)
		Transform SOAP response to JSON

	Deployment is done via maven build
		
### API Manager
	Downloaded and configured to call ESB REST endpoint (proxy)
	

### SOAP UI
	Two tests added one for happy path and other for negative case.


### Issues so far
** The API manager is rejecting request for authentication token even if correct token and header key is used, it never validates the token. The API is published and added subscription.
	
** The validate mediator failsT - he mediators throws unsupported operation OMException error at runtime when it reach to validate. The json schema file was added in repository at _system/config

	 <validate>
      <schema key="conf:/getcustomer.json"/>
      <on-fail>
      <log description="">
        <property name="message" value=“JSON schema validation failed”/>
      </log>
      </on-fail>
    </validate>


### GitHub. 
  The work is committed at public gitbub project. https://github.com/dineshkadm/WSO2-APi-Integrator

### Build

#### Enterprise Integrator
  go to IntegratorAPI
	man clean install
 
#### Proxy
  go to IntegratorProxy
  change <trustStorePath> of your wso2carbon.jks
	mvn clean deploy -Dmaven.deploy.skip=true -Dmaven.car.deploy.skip=false	

#### Wiremock stub
	Run wiremock 
		java -jar wiremock-standalone-2.20.0.jar
	create stub
		mvn clean install
 
### How to run

#### API manager - version 2.6
	./wso2server.sh -DportOffset=7

#### Start carbon
	./carbon.sh 

#### Start integrator - version 6.4
	./integrator.sh 

#### SOAP UI
	open the project in soap UI
