package reader;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.FileReader;
import org.apache.avro.file.SeekableInput;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.mapred.FsInput;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class AvroReader {

    public static void main(String[] args) throws IOException {
        Path path = new Path("hdfs://localhost:9000/user/ashish/Twitter_Data/FlumeData.1449730709818");
        Configuration config = new Configuration();
        SeekableInput input = new FsInput(path, config);
        DatumReader<GenericRecord> reader = new GenericDatumReader<>();
        FileReader<GenericRecord> fileReader = DataFileReader.openReader(input, reader);

        for (GenericRecord datum : fileReader) {
            System.out.println("value = " + datum);
        }
        fileReader.close();
    }
}