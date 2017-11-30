![Corda](https://www.corda.net/wp-content/uploads/2016/11/fg005_corda_b.png)

# Ping-Pong CorDapp

This CorDapps allows a node to ping any other node on the network.

# Pre-requisites:
  
See https://docs.corda.net/getting-set-up.html.

# Usage

## Running the nodes:

See https://docs.corda.net/tutorial-cordapp.html#running-the-example-cordapp.

## Pinging a node:

### RPC via Gradle:

Run the following command from within the root of the project:

* Unix/Mac OSX: `./gradlew pingPartyB`
* Windows: `gradlew pingPartyB`

You should see the following message, indicating that PartyB responded to your ping:

    `Successfully pinged O=PartyB,L=New York,C=US.`.

### RPC via IntelliJ:

Run the `Run Ping-Pong RPC Client` run configuration from IntelliJ.

You should see the following message, indicating that PartyB responded to your ping:

    `Successfully pinged O=PartyB,L=New York,C=US.`.