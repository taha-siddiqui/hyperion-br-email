# hyperion-br-email
Generate an email from a Hyperion business rule using Groovy

DISCLAIMER:
===========
THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

INTRODUCTION:
=============
This Groovy script is intended to be used to generate a customized email using a Groovy script executed from a Hyperion Calculation Manager business rule.

COMPONENTS:
===========
The following components are needed to configure and execute the Groovy script from a Calculation Manager rule:
NB: JAR files are not being supplied and should be downloaded from the appropriate repository (e.g. Maven).
(a) Updated JAR file: mailapi.jar
(b) Updated JAR file: smtp.jar
(c) JAR file: activation-1.1.1.jar
(d) JAR file: javax-mail-1.5.6jar
(e) JAR file: groovy-all-2.4.7.jar
(f) Updated groovyaccess.properties file
(g) Updted groovycdf.properties
(h) Sample groovy script to generate email - hypbremailexample.groovy
(i) Sample Calculation Manager rule to execute sample groovy script - hypbrexample.txt

CONFIGURATION:
==============

The following steps should be following to configure the example scripts for testing:
(a) STOP the Hyperion Essbase Server
(b) Create a new directory (ie. /bkup) in the following directory:
-- app/oracle/epm/EPMSystem11R1/products/Essbase/EssbaseServer/java/lib
(c) Move the following JAR files to the directory shown below:
-- mailapi.jar
-- smtp.jar
-- move to: /app/oracle/epm/EPMSystem11R1/products/Essbase/EssbaseServer/java/lib/bkup
(d) Go to a repository (e.g. Maven) and download the following JAR files -- Hyperion Essbase only supports JRE 1.6 so any downloaded JARs must be compatible with this JRE:
-- javax.mail-1.5.6.jar
-- mailapi.jar
-- smtp.jar
-- activation-1.1.1.jar
-- groovy-all-2.4.7.jar
(e) Copy the following JAR files to the directory shown below:
-- javax.mail-1.5.6.jar
-- mailapi.jar
-- smtp.jar
-- copy to: /app/oracle/epm/EPMSystem11R1/products/Essbase/EssbaseServer/java/lib
(f) Copy the following JAR files to the directory shown:
-- javax.mail-1.5.6.jar
-- activation-1.1.1.jar
-- groovy-all-2.4.7.jar
-- copy to: /Oracle/Middleware/EPMSystem11R1/products/Essbase/EssbaseServer/java/udf
(g) Update your groovyaccess.properties file to whitelist the packages in the new JAR files -- for testing purposes use the provided sample
(h) By default staticcompile and sandboxing are set to true in the groovycdf.properties file for groovy scripts in Hyperion. We will override this by setting the values to false -- for testing purposes use the supplied groovycdf.properties file.
(i) RESTART the Essbase server and confirm that it starts without errors.
(j) Create a new directory on the Essbase server and download the sample groovy script to this directory. Note the name of this directory and it will be needed to update the sample Calculation Manager business rule.
(k) Create a new Calculation Manager rule and paste in the text from the sample Calculation Manager rule. Remember to update the full path of the sample groovy script. Validate and save the Calculation Manager rules file.
(l) Execute the new Calculation Manager and confirm via Job Console that it ran successfully. After a 2-3 mins you should receive an inbound email at the address specified in the sample groovy script file.
