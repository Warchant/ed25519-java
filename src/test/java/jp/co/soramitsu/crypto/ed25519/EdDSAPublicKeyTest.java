/**
 * EdDSA-Java by str4d
 *
 * To the extent possible under law, the person who associated CC0 with
 * EdDSA-Java has waived all copyright and related or neighboring rights
 * to EdDSA-Java.
 *
 * You should have received a copy of the CC0 legalcode along with this
 * work. If not, see <https://creativecommons.org/publicdomain/zero/1.0/>.
 */
package jp.co.soramitsu.crypto.ed25519;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.security.spec.X509EncodedKeySpec;
import javax.xml.bind.DatatypeConverter;
import jp.co.soramitsu.crypto.ed25519.spec.EdDSAPublicKeySpec;
import org.junit.Test;

/**
 * @author str4d
 */
public class EdDSAPublicKeyTest {
    /**
     * The example public key MCowBQYDK2VwAyEAGb9ECWmEzf6FQbrBZ9w7lshQhqowtrbLDFw4rXAxZuE=
     * from https://tools.ietf.org/html/draft-ietf-curdle-pkix-04#section-10.1
     */
    static final byte[] TEST_PUBKEY = DatatypeConverter.parseHexBinary("302a300506032b657003210019bf44096984cdfe8541bac167dc3b96c85086aa30b6b6cb0c5c38ad703166e1");

    static final byte[] ENCODED = DatatypeConverter.parseHexBinary("19bf44096984cdfe8541bac167dc3b96c85086aa30b6b6cb0c5c38ad703166e1");

    static final byte[] TEST_PUBKEY_NULL_PARAMS = DatatypeConverter.parseHexBinary("302c300706032b6570050003210019bf44096984cdfe8541bac167dc3b96c85086aa30b6b6cb0c5c38ad703166e1");
    static final byte[] TEST_PUBKEY_OLD = DatatypeConverter.parseHexBinary("302d300806032b65640a010103210019bf44096984cdfe8541bac167dc3b96c85086aa30b6b6cb0c5c38ad703166e1");

    @Test
    public void testDecodeAndEncode() throws Exception {
        // Decode
        X509EncodedKeySpec encoded = new X509EncodedKeySpec(TEST_PUBKEY);
        EdDSAPublicKey keyIn = new EdDSAPublicKey(encoded);

        // Encode
        EdDSAPublicKeySpec decoded = new EdDSAPublicKeySpec(
                keyIn.getA(),
                keyIn.getParams());
        EdDSAPublicKey keyOut = new EdDSAPublicKey(decoded);

    // Check
    assertThat(keyOut.getEncoded(), is(equalTo(
            ENCODED
          )));
  }

    @Test
    public void testDecodeWithNullAndEncode() throws Exception {
        // Decode
        X509EncodedKeySpec encoded = new X509EncodedKeySpec(TEST_PUBKEY_NULL_PARAMS);
        EdDSAPublicKey keyIn = new EdDSAPublicKey(encoded);

        // Encode
        EdDSAPublicKeySpec decoded = new EdDSAPublicKeySpec(
                keyIn.getA(),
                keyIn.getParams());
        EdDSAPublicKey keyOut = new EdDSAPublicKey(decoded);

        // Check
        assertThat(keyOut.getEncoded(), is(equalTo(
            ENCODED
        )));
    }

    @Test
    public void testReEncodeOldEncoding() throws Exception {
        // Decode
        X509EncodedKeySpec encoded = new X509EncodedKeySpec(TEST_PUBKEY_OLD);
        EdDSAPublicKey keyIn = new EdDSAPublicKey(encoded);

        // Encode
        EdDSAPublicKeySpec decoded = new EdDSAPublicKeySpec(
                keyIn.getA(),
                keyIn.getParams());
        EdDSAPublicKey keyOut = new EdDSAPublicKey(decoded);

        // Check
        assertThat(keyOut.getEncoded(), is(equalTo(
            ENCODED
        )));
    }
}
