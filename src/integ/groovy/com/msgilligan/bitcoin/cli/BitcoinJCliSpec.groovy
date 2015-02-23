package com.msgilligan.bitcoin.cli

/**
 * Test Spec for the bitcoinj-cli tool
 *
 * TODO: We should probably check the command output (eventually)
 */
class BitcoinJCliSpec extends BaseCLISpec {

    def "help option"() {
        when:
        def result = command '--help'

        then:
        result.status == 1
        result.output.length() > 0
        result.error.length() == 0
    }

    def "get block count"() {
        when:
        def result = command '-regtest getblockcount'

        then:
        result.status == 0
        result.output.length() > 0
        Integer.parseInt(result.output[0..-2]) > 0 // blockcount is a valid integer 0 or greater
        result.error.length() == 0
    }

    def "generate a block"() {
        when:
        def result = command '-regtest setgenerate 1'

        then:
        result.status == 0
        result.output.length() == 0
        result.error.length() == 0
    }

    /**
     * Helper method to create and run a command
     *
     * @param line The command args in a single string, separated by spaces
     * @return  status and output streams in Strings
     */
    protected CommandResult command(String line) {
        String[] args = parseCommandLine(line)     // Parse line into separate args

        // Run the command
        BitcoinJCli cli = new BitcoinJCli(args)
        return runCommand(cli)
    }

}